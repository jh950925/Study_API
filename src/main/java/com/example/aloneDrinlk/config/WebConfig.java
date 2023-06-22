package com.example.aloneDrinlk.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/kakao/login") // 해당 URL에 대해 CORS 설정을 추가
                .allowedOrigins("https://accounts.kakao.com") // 허용할 도메인을 지정
                .allowedMethods("GET"); // 허용할 HTTP 메서드를 지정
    }
}

