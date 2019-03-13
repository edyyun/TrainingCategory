package com.example.categorymongo;

import com.example.categorymongo.Entities.ApiKeyHandlerMethodArgumentResolver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.reactive.config.WebFluxConfigurer;
import org.springframework.web.reactive.result.method.annotation.ArgumentResolverConfigurer;

@SpringBootApplication
public class CategoryMongoApplication implements WebFluxConfigurer {

	@Override
	public void configureArgumentResolvers(ArgumentResolverConfigurer configurer) {
		configurer.addCustomResolver(new ApiKeyHandlerMethodArgumentResolver());
	}

	public static void main(String[] args) {
		SpringApplication.run(CategoryMongoApplication.class, args);
	}

}
