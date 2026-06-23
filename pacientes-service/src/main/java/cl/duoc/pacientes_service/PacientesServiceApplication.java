package cl.duoc.pacientes_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@OpenAPIDefinition(
		info = @Info(
				title = "API DE PACIENTES",
				version = "1.0.1",
				description = "API para CRUD de PACIENTES",
				contact =  @Contact(
						name = "Nicolas Romero ",
						email = "nic.romerog@duocuc.cl"
				)
		)
)

@EnableDiscoveryClient
@SpringBootApplication
public class PacientesServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(PacientesServiceApplication.class, args);
	}

}
