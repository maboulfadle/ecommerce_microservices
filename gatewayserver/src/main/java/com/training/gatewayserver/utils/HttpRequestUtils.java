package com.training.gatewayserver.utils;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;

/**
 * The {@link HttpRequestUtils} class contains bench of methods that handles the {@link ServerHttpRequest}.
 *
 * @author mohammed
 * @since 2024.08
 */
public class HttpRequestUtils {

    public static final String TRACE_ID = "Trace-Id";

    /**
     * Gets trace id.
     *
     * @param exchange the exchange
     * @return the trace id
     */
    public static String getTraceId(final ServerWebExchange exchange) {
        return exchange.getRequest()
                .getHeaders()
                .getFirst(TRACE_ID);
    }
}
