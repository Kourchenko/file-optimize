package com.kourchenko.fileoptimize.util;

import java.util.Collections;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class FileExtensionMap {

    public static final Map<String, String> extensionMap;
    static {
        Map<String, String> tempExtensionMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);

        tempExtensionMap.put("doc", "application/msword");
        tempExtensionMap.put("dot", "application/msword");
        tempExtensionMap.put("docx",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.document");
        tempExtensionMap.put("dotx",
                "application/vnd.openxmlformats-officedocument.wordprocessingml.template");
        tempExtensionMap.put("docm", "application/vnd.ms-word.document.macroEnabled.12");
        tempExtensionMap.put("dotm", "application/vnd.ms-word.template.macroEnabled.12");
        tempExtensionMap.put("xls", "application/vnd.ms-excel");
        tempExtensionMap.put("xlt", "application/vnd.ms-excel");
        tempExtensionMap.put("xla", "application/vnd.ms-excel");
        tempExtensionMap.put("xlsx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        tempExtensionMap.put("xltx",
                "application/vnd.openxmlformats-officedocument.spreadsheetml.template");
        tempExtensionMap.put("xlsm", "application/vnd.ms-excel.sheet.macroEnabled.12");
        tempExtensionMap.put("xltm", "application/vnd.ms-excel.template.macroEnabled.12");
        tempExtensionMap.put("xlam", "application/vnd.ms-excel.addin.macroEnabled.12");
        tempExtensionMap.put("xlsb", "application/vnd.ms-excel.sheet.binary.macroEnabled.12");
        tempExtensionMap.put("ppt", "application/vnd.ms-powerpoint");
        tempExtensionMap.put("pot", "application/vnd.ms-powerpoint");
        tempExtensionMap.put("pps", "application/vnd.ms-powerpoint");
        tempExtensionMap.put("ppa", "application/vnd.ms-powerpoint");
        tempExtensionMap.put("pptx",
                "application/vnd.openxmlformats-officedocument.presentationml.presentation");
        tempExtensionMap.put("potx",
                "application/vnd.openxmlformats-officedocument.presentationml.template");
        tempExtensionMap.put("ppsx",
                "application/vnd.openxmlformats-officedocument.presentationml.slideshow");
        tempExtensionMap.put("ppam", "application/vnd.ms-powerpoint.addin.macroEnabled.12");
        tempExtensionMap.put("pptm", "application/vnd.ms-powerpoint.presentation.macroEnabled.12");
        tempExtensionMap.put("potm", "application/vnd.ms-powerpoint.presentation.macroEnabled.12");
        tempExtensionMap.put("ppsm", "application/vnd.ms-powerpoint.slideshow.macroEnabled.12");
        // Open Office
        tempExtensionMap.put("odt", "application/vnd.oasis.opendocument.text");
        tempExtensionMap.put("ott", "application/vnd.oasis.opendocument.text-template");
        tempExtensionMap.put("oth", "application/vnd.oasis.opendocument.text-web");
        tempExtensionMap.put("odm", "application/vnd.oasis.opendocument.text-master");
        tempExtensionMap.put("odg", "application/vnd.oasis.opendocument.graphics");
        tempExtensionMap.put("otg", "application/vnd.oasis.opendocument.graphics-template");
        tempExtensionMap.put("odp", "application/vnd.oasis.opendocument.presentation");
        tempExtensionMap.put("otp", "application/vnd.oasis.opendocument.presentation-template");
        tempExtensionMap.put("ods", "application/vnd.oasis.opendocument.spreadsheet");
        tempExtensionMap.put("ots", "application/vnd.oasis.opendocument.spreadsheet-template");
        tempExtensionMap.put("odc", "application/vnd.oasis.opendocument.chart");
        tempExtensionMap.put("odf", "application/vnd.oasis.opendocument.formula");
        tempExtensionMap.put("odb", "application/vnd.oasis.opendocument.database");
        tempExtensionMap.put("odi", "application/vnd.oasis.opendocument.image");
        tempExtensionMap.put("oxt", "application/vnd.openofficeorg.extension");

        tempExtensionMap.put("txt", "text/plain");
        tempExtensionMap.put("rtf", "application/rtf");
        tempExtensionMap.put("pdf", "application/pdf");
        tempExtensionMap.put("svg", "image/svg+xml");

        tempExtensionMap.put("fif", "image/fif");
        tempExtensionMap.put("ico", "image/x-icon");
        tempExtensionMap.put("gif", "image/gif");
        tempExtensionMap.put("ief", "image/ief");
        tempExtensionMap.put("ifs", "image/ifs");
        tempExtensionMap.put("jpeg", "image/jpeg");
        tempExtensionMap.put("jpg", "image/jpeg");
        tempExtensionMap.put("png", "image/png");
        tempExtensionMap.put("tiff", "image/tiff");
        tempExtensionMap.put("tif", "image/tiff");
        tempExtensionMap.put("dwg", "image/vnd");
        tempExtensionMap.put("svf", "image/vnd");
        tempExtensionMap.put("wi", "image/wavelet");
        tempExtensionMap.put("bmp", "image/bmp");
        tempExtensionMap.put("pcd", "image/x-photo-cd");
        tempExtensionMap.put("ras", "image/x-cmu-raster");
        tempExtensionMap.put("pnm", "image/x-portable-anymap");
        tempExtensionMap.put("pbm", "image/x-portable-bitmap");
        tempExtensionMap.put("pgm", "image/x-portable-graymap");
        tempExtensionMap.put("ppm", "image/x-portable-pixmap");
        tempExtensionMap.put("rgb", "image/x-rgb");
        tempExtensionMap.put("xbm", "image/x-bitmap");
        tempExtensionMap.put("xpm", "image/x-xpixmap");
        tempExtensionMap.put("xwd", "image/x-xwindowdump");

        tempExtensionMap.put("css", "text/css");
        tempExtensionMap.put("htm", "text/html");
        tempExtensionMap.put("html", "text/html");
        tempExtensionMap.put("txt", "text/plain");
        tempExtensionMap.put("rtx", "text/richtext");
        tempExtensionMap.put("tsv", "text/tab-separated-values");
        tempExtensionMap.put("etx", "text/x-setext");
        tempExtensionMap.put("talk", "text/x-speech");
        tempExtensionMap.put("xml", "text/xml");
        tempExtensionMap.put("xul", "text/xul");

        // Audio
        tempExtensionMap.put("mp3", "audio/mpeg");
        tempExtensionMap.put("aac", "audio/aac");
        tempExtensionMap.put("mp4", "audio/mp4");
        tempExtensionMap.put("mpeg", "audio/mpeg");
        tempExtensionMap.put("wav", "audio/wav");

        // Video
        tempExtensionMap.put("flv", "video/x-flv");
        tempExtensionMap.put("mp4", "video/mp4");
        tempExtensionMap.put("mov", "video/quicktime");
        tempExtensionMap.put("avi", "video/x-msvideo");
        tempExtensionMap.put("wmv", "video/x-ms-wmv");
        tempExtensionMap.put("ogv", "video/ogg");
        tempExtensionMap.put("webm", "video/webm");

        // CSV
        tempExtensionMap.put("csv", "text/csv");

        // AXD
        tempExtensionMap.put("axd", "application/octet-stream");

        extensionMap = Collections.unmodifiableMap(tempExtensionMap);
    }

    private static final Map<String, String> invertedMap;

    static {
        Map<String, String> tempInvertedMap = extensionMap.entrySet().stream()
                .collect(Collectors.toMap(Map.Entry::getValue, Map.Entry::getKey, (key1, key2) -> {
                    return key1;
                }));

        invertedMap = Collections.unmodifiableMap(tempInvertedMap);
    }

    public static String reverseLookup(String value) {
        return invertedMap.get(value);
    }
}
