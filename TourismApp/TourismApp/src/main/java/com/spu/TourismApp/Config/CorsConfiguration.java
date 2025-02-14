package com.spu.TourismApp.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration // Marks this class as a configuration class for Spring Boot
public class    CorsConfiguration implements WebMvcConfigurer {

    /**
     * Configures Cross-Origin Resource Sharing (CORS) settings for the application.
     * CORS allows web applications running on different domains to interact with the backend.
     *
     * @param registry The CorsRegistry instance that manages CORS mappings.
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") // Applies CORS settings to all endpoints
                .allowedOriginPatterns("*") // Allows requests from any origin
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Specifies allowed HTTP methods
                .allowedHeaders("*") // Allows all headers in requests
                .exposedHeaders("Authorization"); // Exposes the Authorization header to the client
    }
}
