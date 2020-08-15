package deveficiente.mercadolivre;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("deveficiente.mercadolivre"))
                .paths(PathSelectors.any())
                .build()
                .ignoredParameterTypes(UserDetails.class)
                .securitySchemes(List.of(new ApiKey("JWT", "Authorization", "header")))
                .securityContexts(List.of(securityContext()))
                .apiInfo(apiInfo());
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(
                        Arrays.asList(new SecurityReference("JWT", new AuthorizationScope[]{})))
                .forPaths(PathSelectors.regex("/api.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("API do Mercado Livre")
                .version("1.0.0")
                .contact(new Contact("Bruno Tatsumi", "https://github.com/tatsumibruno", "bruno.yokio@gmail.com"))
                .build();
    }
}
