package com.training.orders.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The {@link Response} class contains response data information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Data
@AllArgsConstructor
@Schema(name = "Response", description = "Schema that holds successful response information")
public class Response {

    @Schema(description = "Status code in the response", example = "201")
    private int statusCode;

    @Schema(description = "Status message in the response", example = "Product created successfully")
    private String message;
}
