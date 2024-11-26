package kr.kro.hereinkorea.global.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // 모든 엔드포인트 허용
                .allowedOrigins("http://localhost:3000")  // React 도메인 허용
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}