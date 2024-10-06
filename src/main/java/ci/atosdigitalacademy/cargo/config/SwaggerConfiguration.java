package ci.atosdigitalacademy.cargo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI getOpenAPI() {
        final Info info = new Info()
                .title("cargoAPIs")
                .version("0.0.1-SNAPSHOT")
                .description("Theses APIs exposes cargo endpoints");
        return new OpenAPI().info(info);
    }
}
