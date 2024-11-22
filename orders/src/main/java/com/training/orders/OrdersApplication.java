package com.training.orders;

import com.training.orders.properties.ContactProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * The {@link OrdersApplication} class represents the starting-point of the order microservice.
 *
 * @author mohammed
 * @since 2024.08
 */
@SpringBootApplication
@EnableConfigurationProperties(value = {ContactProperties.class})
@EnableFeignClients
public class OrdersApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrdersApplication.class, args);
    }
}
