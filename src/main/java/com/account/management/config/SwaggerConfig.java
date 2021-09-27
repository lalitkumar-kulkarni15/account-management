package com.account.management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Account-management-API")
                .description("The account management API has capability to create, update and fetch the bank account of the customer. It can as well transfer funds from one valid acount" +
                                "to another account across different currencies. It invokes a third party API to get the currency exchange rates while performing the fund transfer.")
                .contact(new Contact("Lalit Kulkarni", "https://www.linkedin.com/in/lalitkumar-kulkarni-42159532/", "lalitkulkarniofficial@gmail.com"))
                .build();

    }

    @Bean
    public Docket swaggerImplementation() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage("com.account.management.controller"))
                .build().apiInfo(apiInfo());
    }
}