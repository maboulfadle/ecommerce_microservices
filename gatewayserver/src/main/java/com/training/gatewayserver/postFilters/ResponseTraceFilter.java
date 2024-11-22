package com.training.gatewayserver.postFilters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static com.training.gatewayserver.utils.HttpRequestUtils.TRACE_ID;
import static com.training.gatewayserver.utils.HttpRequestUtils.getTraceId;
import static reactor.core.publisher.Mono.fromRunnable;

/**
 * The {@link ResponseTraceFilter} class represents a filter that generate
 * the correlationId in {@link org.springframework.http.server.reactive.ServerHttpResponse}.
 *
 * @author mohammed
 * @since 2024.08
 */
@Configuration
@Slf4j
public class ResponseTraceFilter {

    @Bean
    public GlobalFilter responseTraceIdFilter() {
        return (exchange, chain) ->
            chain.filter(exchange).then(fromRunnable(() -> {
                final String traceId = getTraceId(exchange);
                log.debug("Updated the trace id to the outbound headers: {}", traceId);

                exchange.getResponse()
                        .getHeaders()
                        .addIfAbsent(TRACE_ID, traceId);}
            ));
    }
}
