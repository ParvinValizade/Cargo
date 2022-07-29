package com.company.parceldeliveryapp.controller;

import com.company.parceldeliveryapp.dto.CreateOrderRequest;
import com.company.parceldeliveryapp.dto.OrderDto;
import com.company.parceldeliveryapp.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/order")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequest request){
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping("/{mail}")
    public ResponseEntity<List<OrderDto>> getAllOrders(@PathVariable String mail){
        return ResponseEntity.ok(orderService.getAllOrders(mail));
    }
}
