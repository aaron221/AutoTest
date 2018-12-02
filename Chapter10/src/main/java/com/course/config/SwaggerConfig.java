package com.course.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration//声明当前类是一个配置类，相当于一个Spring的XML配置文件,让Spring来加载该类配置，与@Bean配合使用
//首先，将所有标注@Configuration的Bean筛选出来；
//其次，对每个配置类使用CGlib进行增强；
//对方法调用进行代理，防止嵌套调用时出现多个不同实例，具体可查看ConfigurationClassEnhancer；
@EnableSwagger2//注解是用来来启用Swagger2--用来生成接口文档
public class SwaggerConfig {

    @Bean
    public Docket api(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .pathMapping("/")
                .select()
                .paths(PathSelectors.regex("/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return  new ApiInfoBuilder().title("我的接口文档")
                .contact(new Contact("dazhou","","42197393@qq.com"))
                .description("这是我的swaggerui生成的接口文档")
                .version("1.0.0.0")
                .build();

    }
}
