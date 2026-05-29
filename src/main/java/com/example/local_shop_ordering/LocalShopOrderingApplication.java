package com.example.local_shop_ordering;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info =@Info(
				title = "Local-Shop-Ordering REST API Documentation",
				description = "Local-Shop-Ordering REST API Documentation",
				version = "v1",
				contact = @Contact(
						name = "Aman Raj",
						email = "aman@raj.com",
						url = "https://www.localshopordering.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "https://www.localshopordering.com"
				)
		),
		externalDocs = @ExternalDocumentation(
				description =  "Local-Shop-Ordering REST API Documentation",
				url = "https://www.localshopordering.com/swagger-ui.html"
		)
)
public class LocalShopOrderingApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalShopOrderingApplication.class, args);
	}

}
