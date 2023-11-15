package da.indian.cities.geo.location.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.DocExpansion;
import springfox.documentation.swagger.web.UiConfiguration;
import springfox.documentation.swagger.web.UiConfigurationBuilder;

@Configuration
public class SwaggerConfig {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("DA-API-CITIES")
                .select()
                .apis(RequestHandlerSelectors.basePackage("da.indian.cities.geo.location"))
                .build();
    }

    @Bean
    public ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("DA-API-CITIES")
                .version("1.0.0")
                .description("Indian Cities Geo-Locations - Latitude Longitude")
                .build();
    }

    @Bean
    public UiConfiguration setupSwaggerUI() {
        return UiConfigurationBuilder.builder()
                .docExpansion(DocExpansion.LIST)
                .defaultModelsExpandDepth(-1)
                .build();
    }

}
