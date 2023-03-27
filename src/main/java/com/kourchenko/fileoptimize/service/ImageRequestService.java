package com.kourchenko.fileoptimize.service;

import java.awt.image.BufferedImage;

import java.io.IOException;

import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import javax.imageio.ImageIO;
import org.springframework.stereotype.Service;

import com.kourchenko.fileoptimize.exception.BadRequestException;

@Service
public class ImageRequestService {

    /**
     * Given a URL, dowload the image into a BufferedImage.
     *
     * @param urlString The image URL.
     * @return BufferedImage
     */
    public BufferedImage getBufferedFileFromURL(String urlString) throws BadRequestException {
        try {
            // replaces spaces in urls so that they cant be downloaded
            URI uri = new URI(urlString.replaceAll(" ", "%20"));
            // Percent-encode non-ASCII characters to US-ASCII string
            URL url = new URL(uri.toASCIIString());

            return getBufferedFileFromURL(url);
        } catch (URISyntaxException e) {
            throw new BadRequestException("[ImageRequestService.getBufferedFileFromURL]", e);
        } catch (IOException e) {
            throw new BadRequestException("[ImageRequestService.getBufferedFileFromURL]", e);
        }
    }

    /**
     * Given a URL, dowload the image into a BufferedImage.
     *
     * @param url The URL.
     * @return BufferedImage
     */
    public BufferedImage getBufferedFileFromURL(URL url) throws BadRequestException {
        try {
            HttpURLConnection huc = (HttpURLConnection) url.openConnection();
            huc.setRequestProperty("User-Agent",
                    "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
            huc.setRequestMethod("GET");
            huc.connect();

            return ImageIO.read(huc.getInputStream());
        } catch (IOException e) {
            throw new BadRequestException("[ImageRequestService.getBufferedFileFromURL]", e);
        }
    }
}
