package com.training.orders.controllers;

import com.training.orders.data.ErrorResponse;
import com.training.orders.data.OrderData;
import com.training.orders.data.OrderItemData;
import com.training.orders.properties.ContactProperties;
import com.training.orders.services.IOrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


/**
 * The {@link OrderController} class exposes the RESTFULL endpoints to handle order information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Tag(name = "Orders REST APIs", description = "REST APIs to create, place and get orders")
@RestController
@RequestMapping(value = "/api/o/", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class OrderController {

    private final IOrderService orderService;
    private final ContactProperties contactProperties;


    @PostMapping("/initialize")
    public ResponseEntity<OrderData> initializeOrder() {
        return new ResponseEntity<>(orderService.initializeOrder(), HttpStatus.OK);
    }


    @Operation(
        summary = "Add order item REST API",
        description = "REST API to add an order item based on the given product code and quantity"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    @PostMapping("/{orderCode}/entries/add")
    public ResponseEntity<OrderItemData> addOrderItem(@PathVariable final String orderCode,
                                                      @RequestParam final String productCode,
                                                      @RequestParam(required = false, defaultValue = "1") final long quantity) {

        final OrderItemData orderEntry = orderService.addOrderEntry(orderCode, productCode, quantity);
        return new ResponseEntity<>(orderEntry, HttpStatus.OK);
    }


    @Operation(
        summary = "Place an order REST API",
        description = "REST API to place an order based on the order id"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    @PostMapping("/{orderCode}/placeOrder")
    public ResponseEntity<OrderData> placeOrder(@PathVariable final String orderCode) {
        final OrderData order = orderService.placeOrder(orderCode);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @Operation(
        summary = "Fetch order details REST API",
        description = "REST API to get the order details based on the order code"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    @GetMapping("/{orderCode}")
    public ResponseEntity<OrderData> getOrderForCode(@PathVariable final String orderCode) {
        final OrderData order = orderService.getOrder(orderCode);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }


    @Operation(
            summary = "Fetch contact details REST API",
            description = "REST API to get the orders microservice contact details"
    )
    @ApiResponses({
        @ApiResponse(
            responseCode = "200",
            description = "HTTP Status OK"
        ),
        @ApiResponse(
            responseCode = "500",
            description = "HTTP Status Internal Server Error",
            content = @Content(schema = @Schema(implementation = ErrorResponse.class))
        )
    })
    @GetMapping("/contact-info")
    public ResponseEntity<ContactProperties> getContactInfo() {
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(contactProperties);
    }
}
