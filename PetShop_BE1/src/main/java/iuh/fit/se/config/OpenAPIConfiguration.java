package iuh.fit.se.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;

@Configuration
public class OpenAPIConfiguration {
	@Bean
	public OpenAPI defineOpenApi() {
		Server server = new Server();
		server.setUrl("http://localhost:9998");
		server.setDescription("Pet Management REST API Documentation");

		Info information = new Info()
				.title("Pet Management REST API Documentation")
				.version("1.0")
				.description("This API exposes endpoints to manage pets.");
		
		return new OpenAPI().info(information).servers(List.of(server));
	}
}