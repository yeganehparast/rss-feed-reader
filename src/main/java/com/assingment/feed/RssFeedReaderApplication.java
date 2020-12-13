package com.assingment.feed;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@SpringBootApplication
public class RssFeedReaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(RssFeedReaderApplication.class, args);
	}

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("de.mobile.controller"))
				.paths(PathSelectors.any())
				.build()
				.apiInfo(generateApiInfo());
	}


	private ApiInfo generateApiInfo() {
		return new ApiInfoBuilder().title("Backend Coding Challenge")
				.description("This application is developed purposely to assess technical skills.")
				.license("Unlicensed").licenseUrl("http://unlicense.org").version("1.0")
				.contact(
						new Contact("Mehdi Yeganehparast",
								"https://www.linkedin.com/in/mehdi-yeganehparast/",
								"mehdi.yeganehparast@gmail.com"))
				.build();
	}
}
