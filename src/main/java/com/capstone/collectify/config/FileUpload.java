package com.capstone.collectify.config;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;

import javax.servlet.MultipartConfigElement;

@Configuration
public class FileUpload {

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // Maximum request size
        factory.setMaxRequestSize(DataSize.ofMegabytes(1500)); // Adjust the size as needed
        // Maximum file size
        factory.setMaxFileSize(DataSize.ofMegabytes(1500)); // Adjust the size as needed
        return factory.createMultipartConfig();
    }

}
