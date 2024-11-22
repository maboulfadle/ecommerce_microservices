package com.training.gatewayserver;

import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.timelimiter.TimeLimiterConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.circuitbreaker.resilience4j.ReactiveResilience4JCircuitBreakerFactory;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JConfigBuilder;
import org.springframework.cloud.client.circuitbreaker.Customizer;
import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;

import java.time.Duration;
import java.time.LocalDateTime;

import static reactor.core.publisher.Mono.justOrEmpty;

/**
 * The {@link GatewayserverApplication} class represents the starting-point of the gateway server.
 *
 * @author mohammed
 * @since 2024.08
 */
@SpringBootApplication
public class GatewayserverApplication {

	private static final Duration MAX_BACKOFF = Duration.ofSeconds(1);   // the maximum backoff
	private static final Duration INIT_BACKOFF = Duration.ofMillis(100); // the initial backoff
	private static final int FACTOR = 2; 								 // the factor applied on each retry
	private static final boolean BASED_ON_PREVIOUS_BACKOFF = true;       // if true, the next backoff will depends on the previous one
	private static final int NUMBER_OF_RETRIES = 3; 					 // the number of retries

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}

	@Bean
	public RouteLocator routeLocator(final RouteLocatorBuilder routeLocatorBuilder) {
		return routeLocatorBuilder
			.routes()
			.route(predicateSpec -> predicateSpec.path("/ms/products/**")
				.filters(filterSpec -> filterSpec
					.rewritePath("/ms/products/(?<remaining>.*)", "/${remaining}")
					.addResponseHeader("x-response-time", LocalDateTime.now().toString())
					.retry(config -> config
						.setRetries(NUMBER_OF_RETRIES)
						.setMethods(HttpMethod.GET)
						.setBackoff(INIT_BACKOFF, MAX_BACKOFF, FACTOR, BASED_ON_PREVIOUS_BACKOFF)))
				.uri("lb://products"))

			.route(predicateSpec -> predicateSpec.path("/orders/**")
				.filters(filterSpec -> filterSpec
					.rewritePath("/orders/(?<remaining>.*)", "/${remaining}")
					.circuitBreaker(config -> config.setName("ordersCircuitBreaker")
					.setFallbackUri("forward:/api/fallback/contactSupport"))
					.requestRateLimiter(config -> config
						.setRateLimiter(redisRateLimiter())
						.setKeyResolver(userKeyResolver())))
				.uri("lb://orders"))
			.build();
	}

	// Override the default circuit breaker timeout duration
	@Bean
	public Customizer<ReactiveResilience4JCircuitBreakerFactory> circuitFactoryCustomizer() {
		return factory -> factory.configureDefault(id -> new Resilience4JConfigBuilder(id)
				.circuitBreakerConfig(CircuitBreakerConfig.ofDefaults())
				.timeLimiterConfig(TimeLimiterConfig.custom().timeoutDuration(Duration.ofSeconds(2)).build())
				.build());
	}

	@Bean
	public RedisRateLimiter redisRateLimiter() {
		return new RedisRateLimiter(1, 1, 1);
	}

	@Bean
	public KeyResolver userKeyResolver() {
		return exchange -> justOrEmpty(exchange.getRequest()
				.getHeaders()
				.getFirst("user"))
				.defaultIfEmpty("anonymous");
	}
}
