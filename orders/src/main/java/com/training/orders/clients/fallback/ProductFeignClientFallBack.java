package com.training.orders.clients.fallback;

import com.training.orders.clients.ProductFeignClient;
import com.training.orders.data.ProductData;
import org.springframework.stereotype.Component;

/**
 * The {@link ProductFeignClientFallBack} class represents the fallback implementation of {@link ProductFeignClient} interface.
 *
 * @author mohammed
 * @since 2024.08
 */
@Component
public class ProductFeignClientFallBack implements ProductFeignClient {

    @Override
    public ProductData getProduct(final String productCode) {
        return null;
    }
}
