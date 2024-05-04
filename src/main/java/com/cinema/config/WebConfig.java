package com.cinema.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
	
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") // Or specify a path pattern for specific endpoints
						.allowedOrigins("*") // Or specify allowed origins
						.allowedMethods("GET", "POST", "PUT", "DELETE") // etc.
						.allowedHeaders("*");
	}
	
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		// Configure public access for specific endpoints
		registry.addResourceHandler("/screen/**", "/movie/**")
						.addResourceLocations("classpath:/public/")
						.setCachePeriod(3600)
						.resourceChain(true);
	}
}
