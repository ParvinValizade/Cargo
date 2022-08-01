package com.company.parceldeliveryapp.controller;

import com.company.parceldeliveryapp.dto.CreateOrderRequest;
import com.company.parceldeliveryapp.dto.OrderDto;
import com.company.parceldeliveryapp.dto.UpdateOrderDestinationRequest;
import com.company.parceldeliveryapp.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/user/order")
public class UserController {
    private final OrderService orderService;

    public UserController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody CreateOrderRequest request){
        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @PutMapping("/updateDestination")
    public ResponseEntity<OrderDto> updateOrderDestination(@RequestBody UpdateOrderDestinationRequest request){
        return ResponseEntity.ok(orderService.updateOrderDestination(request));
    }

    @GetMapping("/getAllOrders/{mail}")
    public ResponseEntity<List<OrderDto>> getAllOrders(@PathVariable String mail){
        return ResponseEntity.ok(orderService.getAllOrders(mail));
    }

    @GetMapping("/getOrderById/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id){
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @DeleteMapping("/deleteOrderById/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id){
        orderService.deleteOrder(id);
        return ResponseEntity.ok().build();
    }
}
