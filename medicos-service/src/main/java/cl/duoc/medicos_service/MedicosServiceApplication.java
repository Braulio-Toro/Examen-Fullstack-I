package cl.duoc.medicos_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@OpenAPIDefinition(
		info = @Info(
				title = "API DE MEDICOS",
				version = "1.0.1",
				description = "API para CRUD de MEDICOS",
				contact =  @Contact(
						name = "Braulio Toro",
						email = "br.toropalma@duocuc.cl"
				)
		)
)

@EnableDiscoveryClient
@SpringBootApplication
public class MedicosServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MedicosServiceApplication.class, args);
	}

}
