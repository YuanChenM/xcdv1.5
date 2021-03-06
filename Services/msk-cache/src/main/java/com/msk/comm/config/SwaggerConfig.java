package com.msk.comm.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.async.DeferredResult;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api(){
        Docket docket = new Docket(DocumentationType.SWAGGER_2).
                groupName("api").
                genericModelSubstitutes(DeferredResult.class).
                useDefaultResponseMessages(false).
                forCodeGeneration(true).
                select().
                build().
                apiInfo(apiInfo());
        return docket;
    }
    private ApiInfo apiInfo() {
        ApiInfo apiInfo = new ApiInfo("缓存服务器 API",//大标题
                "缓存服务器 REST API, for system administrator",//小标题
                "1.0",//版本
                "",
                "jiang_nan@hoperun.com",//作者
                "本缓存服务提供给内部系统使用",//链接显示文字
                ""//网站链接
        );
        return apiInfo;
    }

}
