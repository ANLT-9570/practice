package com.dg.main.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //swagger要扫描的包路径
                .apis(RequestHandlerSelectors.basePackage("com.dg.main.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
//        return new ApiInfoBuilder()
//                .title("springboot Swagger 测试")
//                .description("Springboot 整合Swagger2")
//                .termsOfServiceUrl("localhost:8080/swagger2")
//                .contact(new Contact("Swagger测试","localhost:8080/ruleengine/swagger-ui.html","baidu@qq.com"))
//                .version("1.0")
//                .build();
        return new ApiInfoBuilder()
                .title("Kitty API Doc")
                .description("This is a restful api document of Kitty.")
                .version("1.0")
                .build();
    }
}
