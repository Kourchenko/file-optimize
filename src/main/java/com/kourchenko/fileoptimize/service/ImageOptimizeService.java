package com.kourchenko.fileoptimize.service;


import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;

import java.net.URL;

import org.apache.commons.lang3.StringUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kourchenko.fileoptimize.exception.BadRequestException;
import com.kourchenko.fileoptimize.util.FileUtils;

@Service
public class ImageOptimizeService {

    @Autowired
    private ImageRequestService imageRequestService;

    public BufferedImage resizeFromUrl(String urlString, Dimension dimension)
            throws BadRequestException {
        BufferedImage bufferedImage = imageRequestService.getBufferedFileFromURL(urlString);

        String fileName = FileUtils.getFileName(urlString);

        return resizeToDimensions(fileName, bufferedImage, dimension);
    }

    public BufferedImage resizeFromUrl(URL url, Dimension dimension) throws BadRequestException {
        BufferedImage bufferedImage = imageRequestService.getBufferedFileFromURL(url);

        String fileName = FileUtils.getFileName(url.getPath());

        return resizeToDimensions(fileName, bufferedImage, dimension);
    }

    public BufferedImage resizeToDimensions(String fileName, BufferedImage bufferedImage,
            Dimension dimension) {
        BufferedImage updatedBufferedImage = resizeToDimensionsSize(bufferedImage, dimension);
        return dropAlphaIfNeeded(updatedBufferedImage, fileName);
    }

    public BufferedImage resizeToDimensions(String fileName, BufferedImage bufferedImage,
            Integer resizeWidth, Integer resizeHeight) {
        Dimension dimension = new Dimension(resizeWidth, resizeHeight);
        BufferedImage updatedBufferedImage = resizeToDimensionsSize(bufferedImage, dimension);
        return dropAlphaIfNeeded(updatedBufferedImage, fileName);
    }

    public BufferedImage resizeToDimensionsSize(BufferedImage originalImage,
            Dimension updatedDimensions) {

        try {
            int width = (int) updatedDimensions.getWidth();
            int height = (int) updatedDimensions.getHeight();

            Image image = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            BufferedImage scaledBI = convertImageToBufferedImage(image);
            scaledBI.getGraphics().drawImage(scaledBI, 0, 0, null);

            return scaledBI;
        } catch (IllegalArgumentException e) {
            throw new BadRequestException("[ImageOptimizeService.resizeToDimensionsSize]", e);
        }
    }

    public BufferedImage dropAlphaIfNeeded(BufferedImage bufferedImage, String fileName) {

        if (StringUtils.endsWithIgnoreCase(fileName, "jpg")
                || StringUtils.endsWithIgnoreCase(fileName, "jpeg")) {
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();

            BufferedImage convertedImg =
                    new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            convertedImg.getGraphics().drawImage(bufferedImage, 0, 0, null);

            return convertedImg;
        }

        return bufferedImage;
    }

    public BufferedImage convertImageToBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }

        int width = image.getWidth(null);
        int height = image.getHeight(null);
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

        // Draw the image on to the buffered image
        Graphics2D graphics2d = bufferedImage.createGraphics();
        graphics2d.drawImage(image, 0, 0, null);
        graphics2d.dispose();

        return bufferedImage;
    }
}
