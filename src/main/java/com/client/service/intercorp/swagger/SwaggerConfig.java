package com.client.service.intercorp.swagger;

import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.client.service.intercorp.config.ApplicationProperties;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.Optional;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Autowired
	private ApplicationProperties properties;

    /**
     * Method for configuration swagger documentation and interface UI.
     * @param environment parameter server
     * @return Docket.class
     */
    @Bean
    Docket docket(Environment environment){
        Docket docket = new Docket(DocumentationType.SWAGGER_2)
                                .select()
                                .apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
                               // .paths(PathSelectors.any())
                                .paths(PathSelectors.ant("/**"))
                                .build()
                                .useDefaultResponseMessages(false)
                                .enableUrlTemplating(false)
                                .forCodeGeneration(true)
                                .ignoredParameterTypes(Throwable.class)
                                .directModelSubstitute(Completable.class, Void.class)
                                .apiInfo(metadata(environment));
        return docket;
    }


    /**
     * Method information documentation API.
     * @param environment
     * @return ApiInfo.class
     */
    private ApiInfo metadata (Environment environment) {
       
        Contact contact = new Contact("Zion S.A.","www.zion.com","denisdevsoa@gmail.com");

        return new ApiInfoBuilder().title(properties.getName())
                                   .description(properties.getDescription())
                                   .version(properties.getVersion())
                                   .license("All Rigths Reserved @Denis")
                                   .build();
    }

}

