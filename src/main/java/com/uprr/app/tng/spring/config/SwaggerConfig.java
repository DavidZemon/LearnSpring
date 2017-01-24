package com.uprr.app.tng.spring.config;

import com.fasterxml.classmate.TypeResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.AlternateTypeRule;
import springfox.documentation.schema.AlternateTypeRules;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collection;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by david on 1/23/17.
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket api () {
        // SpringFox doesn't know how to handle java.util.Collection, so we need to give it a helping hand
        final TypeResolver typeResolver = new TypeResolver();
        final AlternateTypeRule collectionRule = AlternateTypeRules.newRule(
            //replace Collection<T> for any T
            typeResolver.resolve(Collection.class, WildcardType.class),
            //with List<T> for any T
            typeResolver.resolve(List.class, WildcardType.class)
        );

        return new Docket(DocumentationType.SWAGGER_2)
            .alternateTypeRules(collectionRule)
            .directModelSubstitute(CharSequence.class, String.class)
            .directModelSubstitute(Pattern.class, String.class)
            .select()
            .apis(RequestHandlerSelectors.any())
            .paths(PathSelectors.any())
            .build()
            .apiInfo(this.apiInfo());
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Pokemon Guessing Game")
            .description("Guess where the Pokemon are hiding!")
            .version("1.0")
            .build();
    }
}
