package com.training.users.controllers;

import com.training.users.data.AddressDTO;
import com.training.users.data.UserDTO;
import com.training.users.services.IUserService;
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
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;


/**
 * The {@link UserController} class exposes the RESTFULL endpoints to handle order information.
 *
 * @author mohammed
 * @since 2024.08
 */
@Tag(name = "Users REST APIs", description = "REST APIs to create, place and get users")
@RestController
@RequestMapping(value = "/api/u/", produces = {MediaType.APPLICATION_JSON_VALUE})
@RequiredArgsConstructor
public class UserController {

    private final IUserService userService;


    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable final String userId) {
        return new ResponseEntity<>(userService.getUser(userId), HttpStatus.OK);
    }


    @Operation(
        summary = "User registration REST API",
        description = "REST API to register user based on the http request body information "
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
    @PostMapping("/register")
    public ResponseEntity<HttpStatus> register(@RequestBody final UserDTO user) {

        userService.register(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }


    @Operation(
        summary = "Create address REST API",
        description = "REST API to create user address based on the http request body information "
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
    @PostMapping("/{userId}/addresses/create")
    public ResponseEntity<HttpStatus> createAddress(@PathVariable final String userId, @RequestBody final AddressDTO address) {

        userService.createAddress(userId, address);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
