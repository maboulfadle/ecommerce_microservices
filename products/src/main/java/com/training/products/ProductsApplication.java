package com.training.products;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The {@link ProductsApplication} class represents the starting-point of the product microservice.
 *
 * @author mohammed
 * @since 2024.08
 */
@SpringBootApplication
@Slf4j
@OpenAPIDefinition(
    info = @Info(
        title = "Products microservice REST API Documentation",
        description = "Products microservice REST API Documentation",
        version = "v1",
        contact = @Contact(
            name = "ABOULFADLE Mohammed",
            email = "mohammedaboulfadle@gmail.com"
        )
    )
)
public class ProductsApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProductsApplication.class, args);
    }
}
