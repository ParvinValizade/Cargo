package com.company.parceldeliveryapp.controller;

import com.company.parceldeliveryapp.dto.OrderCourierDto;
import com.company.parceldeliveryapp.dto.OrderDto;
import com.company.parceldeliveryapp.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/admin/order")
public class AdminController {
    private final OrderService orderService;

    public AdminController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PutMapping("/changeOrderStatusById/{id}")
    public ResponseEntity<OrderCourierDto> changeOrderStatus(@PathVariable Long id, @RequestBody String status){
        return ResponseEntity.ok(orderService.changeOrderStatusById(id,status));
    }

    @PutMapping("/assignOrderToCourier/{id}")
    public ResponseEntity<OrderCourierDto> assignOrderToCourier(@PathVariable Long id,@RequestBody String mail){
        return ResponseEntity.ok(orderService.assignOrderToCourier(id,mail));
    }
}
