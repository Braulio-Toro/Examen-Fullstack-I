package cl.duoc.api_gateway;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
		info = @Info(
				title = "API DE APIGATEWAY",
				version = "1.0.1",
				description = "API QUE REUNE TODOS LOS MICROSERVICIOS ANTERIORES",
				contact =  @Contact(
						name = "Nicolas Romero y Braulio Toro",
						email = "nic.romerog@duocuc.cl"+
								"br.toropalma@duocuc.cl"
				)
		)
)

@SpringBootApplication
public class 	ApiGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiGatewayApplication.class, args);
	}

}
