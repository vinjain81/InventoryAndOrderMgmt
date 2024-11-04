package com.bgarage.autopartsmgmt.order.controller;

import com.bgarage.autopartsmgmt.autoparts.model.AutoPartEntity;
import com.bgarage.autopartsmgmt.order.dto.OrderRequest;
import com.bgarage.autopartsmgmt.order.dto.OrderResponse;
import com.bgarage.autopartsmgmt.order.model.OrderEntity;
import com.bgarage.autopartsmgmt.order.service.OrderService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping("/Order")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "CREATED", content =
                    {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AutoPartEntity.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    ResponseEntity<OrderResponse> placeOrder(@Valid @RequestBody OrderRequest orderRequest)
    {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderService.placeOrder(orderRequest));
    }

    @GetMapping("/Order")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AutoPartEntity.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    ResponseEntity<List<OrderEntity>> getAllOrders()
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getAllOrders());
    }

    @GetMapping("/Order/{id}")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "OK", content =
                    {
                            @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AutoPartEntity.class)
                            )
                    }),
            @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content),
            @ApiResponse(responseCode = "404", description = "Not Found", content = @Content),
            @ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content)
    })
    ResponseEntity<OrderEntity> getOrderById(@PathVariable Integer id)
    {
        return ResponseEntity.status(HttpStatus.OK)
                .body(orderService.getOrderById(id));
    }
}
