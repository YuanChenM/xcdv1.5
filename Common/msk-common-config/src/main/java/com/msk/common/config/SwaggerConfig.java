package com.msk.common.config;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

import com.msk.common.properties.ApiInfoContactProperties;
import com.msk.common.properties.ApiInfoProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Resource
    private ApiInfoProperties apiInfoProperties;
    @Resource
    private ApiInfoContactProperties apiInfoContactProperties;
    @Bean
    public Docket api(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).
                groupName("邮件服务API").
                genericModelSubstitutes(DeferredResult.class).
                useDefaultResponseMessages(false).
                forCodeGeneration(true).
                select().
                paths(or(regex("/api/.*"))).
                build().
                apiInfo(apiInfo());
        return docket;
    }
    private ApiInfo apiInfo() {
        String title = apiInfoProperties.getTitle();
        String description = apiInfoProperties.getDescription();
        String version = apiInfoProperties.getVersion();
        String termsOfServiceUrl=apiInfoProperties.getTermsOfServiceUrl();
        String name = apiInfoContactProperties.getName();
        String url = apiInfoContactProperties.getUrl();
        String email = apiInfoContactProperties.getEmail();
        String license = apiInfoProperties.getLicense();
        String licenseUrl = apiInfoProperties.getLicenseUrl();
        ApiInfo apiInfo = new ApiInfo(title,//大标题
                description,//小标题
                version,//版本
                termsOfServiceUrl,
                new Contact(name, url, email),//作者
                license,//链接显示文字
                licenseUrl//网站链接
        );
        return apiInfo;
    }

}
