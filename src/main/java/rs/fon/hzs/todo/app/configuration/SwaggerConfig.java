package rs.fon.hzs.todo.app.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {

    @Bean
    Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors
                        .basePackage("rs.fon.hzs.todo.app.controller"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiEndpointsInfo()); //localhost:8080/swagger-ui/
    }

    private ApiInfo apiEndpointsInfo() {
        return new ApiInfoBuilder()
                .title("Todo App workshop")
                .contact(new Contact("Workshop people", "https://google.com", "dean.shawn@gmail.com"))
                .version("5.0.0")
                .build();
    }

}
