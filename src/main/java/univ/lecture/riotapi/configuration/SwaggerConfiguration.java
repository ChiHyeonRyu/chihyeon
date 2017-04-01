package univ.lecture.riotapi.configuration;

import com.google.common.base.Predicate;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static com.google.common.base.Predicates.or;
import static springfox.documentation.builders.PathSelectors.regex;

/**
 * springfox(spring mvc + swagger) doc :
 * http://springfox.github.io/springfox/docs/current/
 * <p/>
 * swagger sample :
 * https://github.com/swagger-api/swagger-codegen/blob/master/samples/server/petstore/spring-mvc/src/main/java/io/swagger/api/PetApi.java
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
    @Value("${application.title}")
    private String applicationTitle;

    @Value("${application.description}")
    private String applicationDescription;

    @Value("${swagger.enabled}")
    private boolean enableSwagger;

    @Bean
    public Docket swaggerSpringMvcPlugin() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(paths())
                .build()
                .apiInfo(apiInfo())
                .enable(enableSwagger);

    }

    private Predicate<String> paths() {
        return or(
                regex("/api.*")
        );
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(applicationTitle)
                .description(applicationDescription)
                .contact(new Contact("riot-kr", "http://www.riotgames.com/", "kor-dev-rg@riotgames.com"))
                .license("Apache License Version 2.0")
                .licenseUrl("https://github.com/springfox/springfox/blob/master/LICENSE")
                .version("2.0")
                .build();
    }

}
