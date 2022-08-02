package com.company.parceldeliveryapp.controller;

import com.company.parceldeliveryapp.dto.*;
import com.company.parceldeliveryapp.service.CourierService;
import com.company.parceldeliveryapp.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/admin/order")
public class AdminController {
    private final OrderService orderService;
    private final CourierService courierService;

    public AdminController(OrderService orderService, CourierService courierService) {
        this.orderService = orderService;
        this.courierService = courierService;
    }

    @PutMapping("/changeOrderStatus/{orderId}")
    public ResponseEntity<OrderCourierDto> changeOrderStatus(@PathVariable("orderId") Long id,@RequestBody UpdateOrderStatusRequest request){
        return ResponseEntity.ok(orderService.changeOrderStatus(id,request));
    }

    @PutMapping("/assignOrderToCourier/{orderId}")
    public ResponseEntity<OrderCourierDto> assignOrderToCourier(@PathVariable("orderId") Long id,@RequestBody AssignOrderToCourierRequest request){
        return ResponseEntity.ok(orderService.assignOrderToCourier(id,request));
    }

    @PostMapping("/createCourier")
    public ResponseEntity<CourierDto> createCourier(@RequestBody CreateCourierRequest request){
        return ResponseEntity.ok(courierService.createCourier(request));
    }

    @GetMapping("/getAllCouriers")
    public ResponseEntity<List<CourierDto>> getAllCouriers(){
        return ResponseEntity.ok(courierService.getAllCouriers());
    }

    @GetMapping("/getAllOrders")
    public ResponseEntity<List<OrderDto>> getAllOrders(){
        return ResponseEntity.ok(orderService.getAllOrders());
    }
}
