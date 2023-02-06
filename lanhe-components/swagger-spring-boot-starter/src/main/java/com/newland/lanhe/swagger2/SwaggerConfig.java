package com.newland.lanhe.swagger2;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.newland.lanhe.swagger2.properties.SwaggerProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
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

/**
 * swagger配置
 * @author leell
 */
@Configuration
@EnableConfigurationProperties(SwaggerProperties.class)
//@ConditionalOnProperty(prefix = "lanhe.swagger2",name = "enable",havingValue = "true")
@EnableSwagger2
@EnableKnife4j
public class SwaggerConfig {
    @Autowired
    private SwaggerProperties swaggerProperties;
    @Bean
    public Docket baseRestApi() {
        if(swaggerProperties.getEnable()==null){
            return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                    .apis(RequestHandlerSelectors.basePackage("com.newland.lanhe.**.controller")).paths(PathSelectors.any())
                    .build();
        }else{
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(buildApiInfo())
                    .select()
                    // 要扫描的API(Controller)基础包
                    .apis(RequestHandlerSelectors.basePackage(swaggerProperties.getBasePackage()))
                    .paths(PathSelectors.any())
                    .build();
        }
    }

    public ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("蓝河接口文档").description("蓝河接口文档Swagger版").termsOfServiceUrl("")
                .contact(new Contact("蓝河", "", "")).version("1.0").build();
    }
    private ApiInfo buildApiInfo() {
        Contact contact = new Contact(swaggerProperties.getConcat().getName(), swaggerProperties.getConcat().getUrl(), swaggerProperties.getConcat().getUrl());
        return new ApiInfoBuilder()
                .title(swaggerProperties.getTitle())
                .description(swaggerProperties.getDescription())
                .contact(contact)
                .version(swaggerProperties.getVersion()).build();
    }
}
