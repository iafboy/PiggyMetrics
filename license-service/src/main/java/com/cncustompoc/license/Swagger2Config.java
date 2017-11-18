package com.cncustompoc.license;
import java.time.LocalDate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
@Configuration
@ComponentScan(basePackages = { "com.cncustompoc.license.*" })
@EnableSwagger2
public class Swagger2Config {
    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.cncustompoc.license"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class, String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false)
                .enableUrlTemplating(true);
    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("cncustompoc Restful APIs document")
                .description("cncustompoc Restful APIs document")
                .termsOfServiceUrl("oracle MW north SC team")
                .contact("oracle").version("1.0").build();
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
