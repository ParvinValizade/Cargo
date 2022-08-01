package com.company.parceldeliveryapp.controller;

import com.company.parceldeliveryapp.dto.OrderCourierDto;
import com.company.parceldeliveryapp.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/courier/order")
public class CourierController {
    private final OrderService orderService;

    public CourierController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/getAllAssignedOrders/{mail}")
    public ResponseEntity<List<OrderCourierDto>> getAllAssignedOrders(@PathVariable String mail){
        return ResponseEntity.ok(orderService.getAllAssignedOrderByCourierMail(mail));
    }
}
