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

    @PutMapping("/changeOrderStatus")
    public ResponseEntity<OrderCourierDto> changeOrderStatus(@RequestBody UpdateOrderStatusRequest request){
        return ResponseEntity.ok(orderService.changeOrderStatus(request));
    }

    @PutMapping("/assignOrderToCourier")
    public ResponseEntity<OrderCourierDto> assignOrderToCourier(@RequestBody AssignOrderToCourierRequest request){
        return ResponseEntity.ok(orderService.assignOrderToCourier(request));
    }

    @PostMapping("/createCourier")
    public ResponseEntity<CourierDto> createCourier(@RequestBody CreateCourierRequest request){
        return ResponseEntity.ok(courierService.createCourier(request));
    }

    @GetMapping("/getAllCouriers")
    public ResponseEntity<List<CourierDto>> getAllCouriers(){
        return ResponseEntity.ok(courierService.getAllCouriers());
    }
}
