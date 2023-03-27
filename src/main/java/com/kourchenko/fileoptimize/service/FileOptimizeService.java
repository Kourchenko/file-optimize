package com.kourchenko.fileoptimize.service;

import org.springframework.stereotype.Service;

import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import com.amazonaws.services.s3.AmazonS3;
import com.kourchenko.fileoptimize.DependencyFactory;
import com.kourchenko.fileoptimize.enums.ResizeMode;
import com.kourchenko.fileoptimize.exception.BadRequestException;
import com.kourchenko.fileoptimize.models.Event;

import com.kourchenko.fileoptimize.util.FileUtils;

@Service
public class FileOptimizeService {

    @Autowired
    private ImageOptimizeService imageOptimizeService;

    @Autowired
    private AmazonS3Service amazonS3Service;

    @Value("${aws.credentials.access.key:''}")
    private String AWS_ACCESS_KEY_ID;

    @Value("${aws.credentials.secret.key:''}")
    private String AWS_SECRET_ACCESS_KEY;

    @Value("${aws.region:}")
    private String AWS_REGION;

    private AmazonS3 s3Client;

    private static final int DEFAULT_WIDTH = 250;
    private static final int DEFAULT_HEIGHT = 250;

    public String processFileEvent(Event event) throws BadRequestException {
        try {

            if (s3Client == null) {
                s3Client = DependencyFactory.s3Client(AWS_ACCESS_KEY_ID, AWS_SECRET_ACCESS_KEY,
                        AWS_REGION);
            }

            String url = event.getURL();
            String srcBucket = event.getSrcBucket();
            Integer width = event.getWidth() != null ? event.getWidth() : DEFAULT_WIDTH;
            Integer height = event.getHeight() != null ? event.getHeight() : DEFAULT_HEIGHT;
            String resizeModeStr = StringUtils.defaultIfBlank(event.getResizeMode(), "FIXED_WIDTH");
            ResizeMode resizeMode = ResizeMode.resizeMode(resizeModeStr);

            // Source S3
            String srcKey =
                    StringUtils.defaultIfBlank(event.getSrcKey(), FileUtils.getFileName(url));

            // Destination S3
            String destBucket = StringUtils.defaultIfBlank(event.getDestBucket(), srcBucket);
            String destKey = StringUtils.defaultIfBlank(event.getDestKey(),
                    FileUtils.createResizedFileName(srcKey, resizeMode, width, height));

            // Check if resized file exists
            if (amazonS3Service.doesObjectExist(s3Client, destBucket, destKey)) {
                return formatS3ObjectUrl(AWS_REGION, destBucket, destKey);
            }

            // Resize file from URL
            Dimension dimension = new Dimension(width, height);
            BufferedImage bufferedImage = imageOptimizeService.resizeFromUrl(url, dimension);

            // Write to resized file
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            String fileName = FileUtils.getFileName(event.getURL());
            String imageType = FilenameUtils.getExtension(fileName);
            ImageIO.write(bufferedImage, imageType, outputStream);
            byte[] fileBytes = outputStream.toByteArray();

            // Upload resized file to S3
            amazonS3Service.putObject(s3Client, fileBytes, destBucket, destKey, imageType);

            return formatS3ObjectUrl(AWS_REGION, destBucket, destKey);
        } catch (IOException e) {
            throw new BadRequestException("[FileOptimizeService.processFileEvent]", e);
        }
    }

    /**
     * Return a full URL path to the S3 Object, for an region, S3 Bucket, S3 Key.
     *
     * @param region String region for S3 Object.
     * @param bucket S3 Bucket
     * @param key S3 Key (aka "file/path/to/image.png")
     * @return Full URL.
     */
    private static String formatS3ObjectUrl(String region, String bucket, String key) {
        return new StringBuilder().append("https://").append(bucket).append(".s3.").append(region)
                .append(".amazonaws.com/").append(key).toString();
    }
}
