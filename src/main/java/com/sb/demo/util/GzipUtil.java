package com.sb.demo.util;

import org.junit.platform.commons.util.StringUtils;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {

    /**
     * 把字符串压缩成字节数组
     * @param content
     * @return byte[]
     * @throws IOException
     */
    public static byte[] compress(String content) throws IOException {
        if (StringUtils.isBlank(content)) {
            return null;
        }
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            try (GZIPOutputStream gzip = new GZIPOutputStream(out)) {
                gzip.write(content.getBytes(StandardCharsets.UTF_8));
            }
            return out.toByteArray();
        }
    }

    /**
     * 把字节数组解压成字符串
     * @param str
     * @return String
     * @throws IOException
     */
    public static String unCompress(byte[] str) throws IOException {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (GZIPInputStream gin = new GZIPInputStream(new ByteArrayInputStream(str))) {
            int b;
            while ((b = gin.read()) != -1) {
                outputStream.write((byte) b);
            }
        }
        return new String(outputStream.toByteArray(), StandardCharsets.UTF_8);
    }
}
