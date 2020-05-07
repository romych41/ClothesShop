package com.kpi.korolova.shop.util;

import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.io.InputStreamReader;

public abstract class Dialect {
//    private static String getSQLScript(Resource resource) {
//
//
//    }

    public static String getSQLScript(String location) {
        DefaultResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resource = resourceLoader.getResource(location);
        //return getSQLScript(resource);
        try {
            String script = FileCopyUtils.copyToString(new InputStreamReader(resource
                    .getInputStream()));
            script = script.replace("\r\n", "\n");
            return script;
        } catch (IOException e) {
            throw new RuntimeException("Can't load resource " + resource.getDescription(), e);
        }
    }
}
