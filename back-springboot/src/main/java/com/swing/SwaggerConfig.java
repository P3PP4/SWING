package com.swing;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	private static final String API_NAME = "SWING";
	private static final String API_VERSION = "0.0.1";
	private static final String API_DESCRIPTION = "APIs of SWING";
	
	@Bean
	public Docket api() {
		Parameter parameterBuilder = new ParameterBuilder()
				.name(HttpHeaders.AUTHORIZATION)
				.description("des HI")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false)
				.build();
		
		List<Parameter> globalParamters = new ArrayList<>();
		globalParamters.add(parameterBuilder);
		
		return new Docket(DocumentationType.SWAGGER_2)
				.globalOperationParameters(globalParamters)
				.apiInfo(apiInfo())
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.swing.user.controller")
						.or(RequestHandlerSelectors.basePackage("com.swing.sentency.controller"))
						.or(RequestHandlerSelectors.basePackage("com.swing.five.controller"))
						.or(RequestHandlerSelectors.basePackage("com.swing.doodle.controller"))
						.or(RequestHandlerSelectors.basePackage("com.swing.note.controller")))
				.paths(PathSelectors.any())
				.build();
	}
	
	public ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title(API_NAME)
				.version(API_VERSION)
				.description(API_DESCRIPTION)
				.build();
	}
	
}