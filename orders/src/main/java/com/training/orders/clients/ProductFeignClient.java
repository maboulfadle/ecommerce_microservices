package com.training.orders.clients;

import com.training.orders.clients.fallback.ProductFeignClientFallBack;
import com.training.orders.data.ProductData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * The {@link ProductFeignClient} interface represents the gateway of products microservice...
 *
 * @author mohammed
 * @since 2024.08
 */
@FeignClient(value = "products", fallback = ProductFeignClientFallBack.class)
public interface ProductFeignClient {

    @GetMapping("/api/p/{productCode}")
    ProductData getProduct(@PathVariable final String productCode);
}
