package cl.duoc.citas_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "API DE CITAS",
				version = "1.0.1",
				description = "API para CRUD de CITAS",
				contact =  @Contact(
						name = "Braulio Toro",
						email = "br.toropalma@duocuc.cl"
				)
		)
)

@EnableDiscoveryClient
@SpringBootApplication
@EnableFeignClients
public class CitasServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CitasServiceApplication.class, args);
	}

}
