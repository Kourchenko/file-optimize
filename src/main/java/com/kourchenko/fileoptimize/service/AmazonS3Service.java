package com.kourchenko.fileoptimize.service;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

import java.io.IOException;

import org.springframework.stereotype.Service;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;

import com.kourchenko.fileoptimize.exception.AmazonException;
import com.kourchenko.fileoptimize.util.FileExtensionMap;

@Service
public class AmazonS3Service {

    /**
     * Check if a S3 Object exists for the S3 Bucket & S3 Key.
     *
     * @param s3Client S3 Client
     * @param bucket S3 Bucket
     * @param key S3 Key (aka "file/path/to/image.png")
     * @return boolean
     */
    public boolean doesObjectExist(AmazonS3 s3Client, String bucket, String key) {
        try {
            return s3Client.doesObjectExist(bucket, key);
        } catch (SdkClientException e) {
            throw new AmazonException("[AmazonS3Service.getObject]", e);
        }
    }

    /**
     * Get S3 Object for the S3 Bucket & S3 Key.
     *
     * @param s3Client S3 Client
     * @param bucket S3 Bucket
     * @param key S3 Key (aka "file/path/to/image.png")
     * @return InputStream
     */
    private S3Object getObject(AmazonS3 s3Client, String bucket, String key) {
        try {
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);
            return s3Client.getObject(getObjectRequest);
        } catch (SdkClientException e) {
            throw new AmazonException("[AmazonS3Service.getObject]", e);
        }
    }

    /**
     * Put S3 Object in S3 Bucket & S3 Key.
     *
     * @param s3Client S3 Client
     * @param outputStream File as ByteArrayOutputStream
     * @param bucket S3 Bucket
     * @param key S3 Key (aka "file/path/to/image.png")
     * @param imageType Image Type
     */
    public void putObject(AmazonS3 s3Client, byte[] fileBytes, String bucket, String key,
            String imageType) {

        try {
            ObjectMetadata metadata = new ObjectMetadata();
            metadata.setContentLength(fileBytes.length);
            metadata.setContentType(FileExtensionMap.extensionMap.get(imageType));
            metadata.setHeader("Cache-Control", "max-age=" + 3600);

            File file = createTempFile(key, fileBytes, imageType);
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucket, key, file)
                    .withCannedAcl(CannedAccessControlList.PublicRead).withMetadata(metadata);

            s3Client.putObject(putObjectRequest);
        } catch (SdkClientException e) {
            throw new AmazonException("[AmazonS3Service.putObject]", e);
        } catch (IOException e) {
            throw new AmazonException("[AmazonS3Service.putObject]", e);
        }
    }

    private File createTempFile(String fileName, byte[] fileBytes, String imageType)
            throws IOException {
        File tempfile = File.createTempFile("temp-file.", imageType);
        writeTempFile(tempfile, fileBytes);
        return tempfile;
    }

    private void writeTempFile(File file, byte[] fileBytes) throws IOException {
        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
        stream.write(fileBytes);
        stream.close();
    }
}
