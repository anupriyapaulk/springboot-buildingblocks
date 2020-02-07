package com.stacksimplify.restservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import io.swagger.annotations.SwaggerDefinition;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	@Bean
	@Primary
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(getAPIInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.stacksimplify.restservices"))
				.paths(PathSelectors.ant("/users/**")).build();
	}
	// Swagger Metadata URL: http://localhost:8080/v2/api-docs
	// Swagger URL: http://localhost:8080/swagger-ui.html
	
	private ApiInfo getAPIInfo() {
		return new ApiInfoBuilder()
		.title("User Management Service")
		.description("This page lists all the APIs of User management service")
		.version("2.0")
		.contact(new Contact("Anu", "http://test.html", "anumail@gmail.com"))
		.license("lic 2.0")
		.licenseUrl("http://license.html")
		.build();
	}
}


