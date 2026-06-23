package cl.duoc.citas_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        Server gatewayServer = new Server();
        gatewayServer.setUrl("http://localhost:9090");
        gatewayServer.setDescription("Citas");
        return new OpenAPI().servers(List.of(gatewayServer));
    }
}
