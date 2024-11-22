package com.training.products.data;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * The {@link ErrorResponse} class contains error response data information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Data
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Schema that holds error response information")
public class ErrorResponse {

    @Schema(description = "The request api path", example = "uri=/api/v1/products/L709962198/delete")
    private  String apiPath;

    @Schema(description = "Error code representing the error happened", example = "NOT_FOUND")
    private HttpStatus errorCode;

    @Schema(description = "Error message representing the error happened", example = "no product found for code L709962198")
    private String message;

    @Schema(description = "Time representing when the error happened", example = "2024-08-06T10:07:54.500271402")
    private LocalDateTime errorTime;
}
