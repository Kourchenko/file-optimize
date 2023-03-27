package com.kourchenko.fileoptimize.util;

import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;

import com.kourchenko.fileoptimize.enums.ResizeMode;
import com.kourchenko.fileoptimize.exception.BadRequestException;

public class FileUtils {
    /**
     * Check if file name extension matches one of the accepted image extensions.
     *
     * @param fileName The file name.
     * @return boolean true if file is an image
     */
    public static boolean fileIsImage(String fileName) {
        String contentType =
                FileExtensionMap.extensionMap.get(FilenameUtils.getExtension(fileName));
        return contentType != null && contentType.contains("image/") && !contentType.contains("svg")
                && !contentType.contains("axd");
    }

    /**
     * Formats the resized file name.
     *
     * "example.png" -> "example_FIXED_WIDTH_500.png"
     *
     * @param fileName The file name.
     * @param resizeMode The {@code ResizeMode}.
     * @param size The resized file size.
     * @return String file name.
     */
    public static String createResizedFileName(String fileName, ResizeMode resizeMode, int width,
            int height) {
        if (StringUtils.isBlank(fileName) || resizeMode == null) {
            return fileName;
        }

        int size = resizeMode == ResizeMode.FIXED_HEIGHT ? height : width;

        String[] fileNameParts = fileName.split("\\.");
        if (fileNameParts.length < 1) {
            return fileName;
        }
        return new StringBuilder().append(fileNameParts[0]).append("_").append(resizeMode)
                .append("_").append(size).append(".").append(fileNameParts[1]).toString();
    }

    /**
     * Get File Name from a provided URL.
     *
     * @param URL The URL.
     * @return String file name.
     */
    public static String getFileName(String URL) throws BadRequestException {
        try {
            URL url = new URL(URL);
            return FilenameUtils.getName(url.getPath());
        } catch (MalformedURLException e) {
            throw new BadRequestException("[FileUtils.getFileName]", e);
        }
    }
}
