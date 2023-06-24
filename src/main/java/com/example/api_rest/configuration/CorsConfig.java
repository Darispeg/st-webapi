package com.example.api_rest.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedOrigins("http://localhost:4200")
                            .allowedMethods("*")
                                .exposedHeaders("*");

                registry.addMapping("/**")
                        .allowedOrigins("https://64968c200342ec2d516dc49e--cute-pie-bd74e5.netlify.app")
                            .allowedMethods("*")
                                .exposedHeaders("*");;

                registry.addMapping("/login")
                                .allowedOrigins("http://localhost:4200")
                                        .allowedMethods("*")
                                                .exposedHeaders("*");

                registry.addMapping("/api/**")
                                .allowedOrigins("http://localhost:4200")
                                        .allowedMethods("*");

                registry.addMapping("/login")
                        .allowedOrigins("https://64968c200342ec2d516dc49e--cute-pie-bd74e5.netlify.app")
                        .allowedMethods("*")
                        .exposedHeaders("*");

                registry.addMapping("/api/**")
                        .allowedOrigins("https://64968c200342ec2d516dc49e--cute-pie-bd74e5.netlify.app")
                        .allowedMethods("*");
            }
        };
    }
}
