package cl.duoc.historial_clinico_service;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@OpenAPIDefinition(
		info = @Info(
				title = "API DE HISTORIAL CLINICO",
				version = "1.0.1",
				description = "API para CRUD de HISTORIAL CLINICO",
				contact =  @Contact(
						name = "Nicolas Romero",
						email = "nic.romerog@duocuc.cl"
				)
		)
)

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class HistorialClinicoServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HistorialClinicoServiceApplication.class, args);
	}

}
