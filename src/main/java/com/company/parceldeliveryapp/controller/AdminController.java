package com.company.parceldeliveryapp.controller;

import com.company.parceldeliveryapp.dto.CourierDto;
import com.company.parceldeliveryapp.dto.CreateCourierRequest;
import com.company.parceldeliveryapp.dto.OrderCourierDto;
import com.company.parceldeliveryapp.service.CourierService;
import com.company.parceldeliveryapp.service.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/admin/order")
public class AdminController {
    private final OrderService orderService;
    private final CourierService courierService;

    public AdminController(OrderService orderService, CourierService courierService) {
        this.orderService = orderService;
        this.courierService = courierService;
    }

    @PutMapping("/changeOrderStatusById/{id}")
    public ResponseEntity<OrderCourierDto> changeOrderStatus(@PathVariable Long id, @RequestBody String status){
        return ResponseEntity.ok(orderService.changeOrderStatusById(id,status));
    }

    @PutMapping("/assignOrderToCourier/{id}")
    public ResponseEntity<OrderCourierDto> assignOrderToCourier(@PathVariable Long id,@RequestBody String mail){
        return ResponseEntity.ok(orderService.assignOrderToCourier(id,mail));
    }

    @PostMapping("/createCourier")
    public ResponseEntity<CourierDto> createCourier(@RequestBody CreateCourierRequest request){
        return ResponseEntity.ok(courierService.createCourier(request));
    }
}
