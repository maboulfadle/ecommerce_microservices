package com.training.gatewayserver.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * The {@link FallbackController} class exposes the product fallback endpoints.
 *
 * @author mohammed
 * @since 2024.08
 */
@RestController
@RequestMapping("/api/fallback")
public class FallbackController {

    @GetMapping("/contactSupport")
    public Mono<String> fallback() {
        return Mono.just("an error has been occurred, please try again later or contact support team.");
    }
}
