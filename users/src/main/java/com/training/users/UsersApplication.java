package com.training.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The {@link UsersApplication} class represents the starting-point of the user microservice.
 *
 * @author mohammed
 * @since 2024.08
 */
@SpringBootApplication
public class UsersApplication {

	public static void main(String[] args) {
		SpringApplication.run(UsersApplication.class, args);
	}

}
