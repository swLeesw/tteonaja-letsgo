package com.ssafy.tteonajaletsgo.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
//import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.annotations.info.Info;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@OpenAPIDefinition(
        info = @Info(
                title = "tteonaja 명세서",
                description = "<h3>tteonaja vue API Reference for Developers</h3>Swagger를 이용한 VUE API<br><img src=\"/logo.png\" width=\"150\">",
                version = "v1",
                contact = @Contact(
                        name = "LEESEUNGWON, KIMMINJU",
                        email = "wlk256032@naver.com",
                        url = "http://github.com/swLeesw/tteonaja-letsgo"
                )
        )
)
@Configuration
public class SwaggerConfiguration {

    @Bean
    public GroupedOpenApi allApi() {
        return GroupedOpenApi.builder().group("ssafy-all").pathsToMatch("/**").build();
    }

//    @Bean
//    public GroupedOpenApi boardApi() {
//        return GroupedOpenApi.builder().group("ssafy-board").pathsToMatch("/board/**").build();
//    }
//
//    @Bean
//    public GroupedOpenApi userApi() {
//        return GroupedOpenApi.builder().group("ssafy-user").pathsToMatch("/user/**").build();
//    }
//
//    @Bean
//    public GroupedOpenApi mapApi() {
//        return GroupedOpenApi.builder().group("ssafy-map").pathsToMatch("/map/*sw*").build();
//    }

}
