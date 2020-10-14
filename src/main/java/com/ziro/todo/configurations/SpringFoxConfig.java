package com.ziro.todo.configurations;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;



@Configuration
public class SpringFoxConfig {
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis( RequestHandlerSelectors.basePackage( "com.ziro.todo" ) )
				.paths(PathSelectors.any())
				.build().apiInfo(metaData());
	}
	
	private ApiInfo metaData() {
		ApiInfo apiInfo = new ApiInfo("Ziro Todo REST API", "Spring Boot REST API for Todo App", "1.0", null,
				new Contact("Team Ziro", "", "gewilira.abiera@yahoo.com"), "Spring Boot 2.3.4.RELEASE",
				null, new ArrayList<>());

		return apiInfo;
	}
}