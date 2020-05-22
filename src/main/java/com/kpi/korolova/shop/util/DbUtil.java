package com.kpi.korolova.shop.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

public class DbUtil {
    public static String encodeImage(MultipartFile image) throws IOException {
        byte[] bytes = new byte[(int)image.getSize()];
        image.getInputStream().read(bytes);
        return Base64.getEncoder().encodeToString(bytes);
    }

    public static byte[] decodeImage(String secret) {
        return Base64.getDecoder().decode(secret);
    }
}
