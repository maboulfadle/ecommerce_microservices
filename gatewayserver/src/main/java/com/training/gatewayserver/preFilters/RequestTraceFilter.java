package com.training.gatewayserver.preFilters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.training.gatewayserver.utils.HttpRequestUtils.TRACE_ID;
import static com.training.gatewayserver.utils.HttpRequestUtils.getTraceId;

/**
 * The {@link RequestTraceFilter} class represents a filter that generate the traceId in {@link ServerHttpRequest}.
 *
 * @author mohammed
 * @since 2024.08
 */
@Component
@Slf4j
public class RequestTraceFilter implements GlobalFilter, Ordered {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, final GatewayFilterChain chain) {
        String traceId = getTraceId(exchange);
        if (traceId != null) {
            log.debug("Trace Id {} found in the request trace filter", traceId);

        } else {
            traceId = UUID.randomUUID().toString();
            setTraceId(exchange, traceId);
            log.debug("Trace Id {} is generated in the request trace filter", traceId);
        }

        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return 1;
    }

    protected void setTraceId(ServerWebExchange exchange, final String correlationId) {
        final ServerHttpRequest request = exchange.getRequest()
                .mutate()
                .header(TRACE_ID, correlationId)
                .build();

        exchange.mutate()
                .request(request)
                .build();

    }
}
