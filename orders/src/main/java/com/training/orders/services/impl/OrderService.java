package com.training.orders.services.impl;

import com.training.orders.clients.ProductFeignClient;
import com.training.orders.data.OrderData;
import com.training.orders.data.OrderItemData;
import com.training.orders.data.ProductData;
import com.training.orders.models.Order;
import com.training.orders.models.OrderItem;
import com.training.orders.repositories.OrderItemRepository;
import com.training.orders.repositories.OrderRepository;
import com.training.orders.services.IOrderService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

import static com.google.common.collect.Lists.newLinkedList;
import static com.training.orders.models.OrderStatus.COMPLETED;
import static com.training.orders.models.OrderStatus.CREATED;
import static com.training.orders.models.PaymentMode.DEBIT_CARD;

/**
 * The {@link OrderService} class is a default implementation of {@link IOrderService} interface.
 *
 * @author mohammed
 * @since 2024.08
 */
@Service
@RequiredArgsConstructor
public class OrderService implements IOrderService {

    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ProductFeignClient productFeignClient;
    private ModelMapper mapper;

    @PostConstruct
    public void init() {
        mapper = new ModelMapper();
    }

    @Override
    public OrderData initializeOrder() {
        final Order order = Order.builder()
                .code(UUID.randomUUID().toString())
                .orderDate(LocalDateTime.now())
                .status(CREATED)
                .items(newLinkedList())
                .build();

         orderRepository.insert(order);

        return mapper.map(order, OrderData.class);
    }

    @Override
    public OrderData getOrder(final String orderCode) {
        final OrderData orderData = mapper.map(orderRepository.findOrderByCode(orderCode), OrderData.class);

        for (final OrderItemData item : orderData.getItems()) {
            final String productCode = item.getProduct().code();
            item.setProduct(productFeignClient.getProduct(productCode));
        }

        return orderData;
    }

    @Override
    public OrderItemData addOrderEntry(final String orderCode, final String productCode, final Long quantity) {
        final Order order = orderRepository.findOrderByCode(orderCode);
        final ProductData product = productFeignClient.getProduct(productCode);

        final OrderItem item = orderItemRepository.findOrderItemByProductCode(productCode)
                .orElseGet(() -> OrderItem.builder()
                    .productCode(productCode)
                    .totalPrice(product.price() * quantity)
                    .quantity(quantity)
                    .build()
                );

        order.getItems().add(item);

         orderRepository.save(order);

        final OrderItemData itemData = mapper.map(item, OrderItemData.class);
        itemData.setProduct(product);

        return itemData;
    }

    @Override
    public OrderData placeOrder(final String orderCode) {
        final Order order = orderRepository.findOrderByCode(orderCode);

        final double price = order.getItems()
                .stream()
                .map(OrderItem::getTotalPrice)
                .mapToDouble(Double::doubleValue)
                .sum();
        order.setTotalPrice(price);

        order.setPaymentMode(DEBIT_CARD);
        order.setStatus(COMPLETED);

         orderRepository.save(order);

        return mapper.map(order, OrderData.class);
    }
}
