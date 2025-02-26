package com.wfarooq.orderservice;

import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(
		info = @Info(
				title = "Order Micro Service For Lidl Clone",
				description = "Order microservice handles receiving, updating, realising, tracking " +
						"and assigning orders to departments and employees with that department",
				version = "V1",
				contact = @Contact(
						name = "Waqar Farooq",
						email = "farooqwaqar18@gmail.com"
				),
				license = @License(
						name = "Apache 2.0",
						url = "www.waqar-portfolio.com"
				)
		),

		externalDocs = @ExternalDocumentation(
		description = "EazyBank Accounts microservices REST API Documentation",
		url = "www.waqar-portfolio.com"
)
)
public class OrderserviceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderserviceApplication.class, args);
	}

}
