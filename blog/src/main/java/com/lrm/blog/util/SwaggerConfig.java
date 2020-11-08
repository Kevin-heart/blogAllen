package com.lrm.blog.util;

import com.google.common.base.Predicates;
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
 * 项 目 名 称：blog
 * 类 名 称：SwaggerConfig
 * 类 描 述：TODO
 * 创建时间：2020/11/9 12:59 AM
 * 创 建 人：huanghao
 *
 * @version: V1.8
 */

/**
 * /**
 *  @Api：修饰整个类，描述Controller的作用
 *  @ApiOperation：描述一个类的一个方法，或者说一个接口
 *  @ApiParam：单个参数描述
 *  @ApiModel：用对象来接收参数
 *  @ApiProperty：用对象接收参数时，描述对象的一个字段
 *  @ApiResponse：HTTP响应其中1个描述
 *  @ApiResponses：HTTP响应整体描述
 *  @ApiIgnore：使用该注解忽略这个API
 *  @ApiError ：发生错误返回的信息
 *  @ApiImplicitParam：一个请求参数
 *  @ApiImplicitParams：多个请求参数
 *
 */
@Configuration //配置类
@EnableSwagger2 //Swagger的开关.表示已经启用Swagger3
public class SwaggerConfig {

    @Bean
    public Docket api() {
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                .host("localhost:8002")// 不配的话，默认当前项目端口
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select() // 选择哪些路径和api会生成document
                .apis(RequestHandlerSelectors.any())// 对所有api进行监控
//                .apis(RequestHandlerSelectors.basePackage("com.hanstrovsky.controller"))// 选择监控的package
//                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))// 只监控有ApiOperation注解的接口
                //不显示错误的接口地址
                .paths(Predicates.not(PathSelectors.regex("/error.*")))//错误路径不监控
                .paths(PathSelectors.regex("/.*"))// 对根下所有路径进行监控
                .build();
        return docket;
}

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("SpringBoot个人博客项目")
                .contact(new Contact("SpringBoot", "https://swagger.io/docs/", "2667275115@qq.com"))
                .description("这是用Swagger动态生成的接口文档")
                .termsOfServiceUrl("https://swagger.io/docs/")
                .license("The Apache License, Version 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0")
                .build();
    }
}
