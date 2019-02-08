package com.example.demo;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;

import com.google.common.collect.Lists;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
 * @author leone.perdigao
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	public static final String AUTHORIZATION_HEADER = "Authorization";
	public static final String DEFAULT_INCLUDE_PATTERN = "/api/.*";
	public static final String V1 = "/api/v1/.*";
	public static final String V2 = "/api/v2/.*";

	@Bean
	public Docket apiDocketV1() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("1.0")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo"))
				.paths(PathSelectors.regex(V1))
				.build()
				.apiInfo(getApiInfo())
				.forCodeGeneration(true)
				.genericModelSubstitutes(ResponseEntity.class)
				.securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.newArrayList(apiKey()))
				.useDefaultResponseMessages(false);
	}
	
	@Bean
	public Docket apiDocketV2() {
		return new Docket(DocumentationType.SWAGGER_2)
				.groupName("2.0")
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.example.demo"))
				.paths(PathSelectors.regex(V2))
				.build()
				.apiInfo(getApiInfo())
				.forCodeGeneration(true)
				.genericModelSubstitutes(ResponseEntity.class)
				.securityContexts(Lists.newArrayList(securityContext())).securitySchemes(Lists.newArrayList(apiKey()))
				.useDefaultResponseMessages(false);
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("Spring Boot + Swagger", "Documentando sua API automaticamente com Swagger", "0.1.0",
				"https://www.apache.org/licenses/LICENSE-2.0",
				new Contact("Leone Perdigao", "https://github.com/leoneperdigao", ""), "Apache License 2.0",
				"https://www.apache.org/licenses/LICENSE-2.0", Collections.emptyList());
	}

	private ApiKey apiKey() {
		return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	}

	private SecurityContext securityContext() {
		return SecurityContext.builder().securityReferences(defaultAuth())
				.forPaths(PathSelectors.regex(DEFAULT_INCLUDE_PATTERN)).build();
	}

	private List<SecurityReference> defaultAuth() {
		AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
		AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
		authorizationScopes[0] = authorizationScope;
		return Lists.newArrayList(new SecurityReference("JWT", authorizationScopes));
	}
}