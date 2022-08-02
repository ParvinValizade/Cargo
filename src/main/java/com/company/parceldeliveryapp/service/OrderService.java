package com.company.parceldeliveryapp.service;

import com.company.parceldeliveryapp.dto.*;
import com.company.parceldeliveryapp.dto.converter.OrderCourierDtoConverter;
import com.company.parceldeliveryapp.dto.converter.OrderDtoConverter;
import com.company.parceldeliveryapp.exception.OrderNotFoundException;
import com.company.parceldeliveryapp.model.Order;
import com.company.parceldeliveryapp.model.Status;
import com.company.parceldeliveryapp.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDtoConverter converter;
    private final OrderCourierDtoConverter orderCourierDtoConverter;

    private final PersonService personService;
    private final CourierService courierService;

    public OrderService(OrderRepository orderRepository, OrderDtoConverter converter, OrderCourierDtoConverter orderCourierDtoConverter, PersonService personService, CourierService courierService) {
        this.orderRepository = orderRepository;
        this.converter = converter;
        this.orderCourierDtoConverter = orderCourierDtoConverter;
        this.personService = personService;
        this.courierService = courierService;
    }

    public OrderDto createOrder(CreateOrderRequest request) {
        personService.checkUserIsExistOrNot(request.getUserMail());
        Order order = new Order(
                request.getName(),
                request.getDestination(),
                Status.ORDERED,
                request.getUserMail()
        );
        return converter.convert(orderRepository.save(order));
    }

    public List<OrderDto> getAllOrders(String mail) {
        return converter.convert(orderRepository.findAllByUserMail(mail));

    }

    public OrderDto getOrderById(Long id){
        return converter.convert(findOrderById(id));
    }

    public OrderDto updateOrderDestination(Long id,UpdateOrderDestinationRequest request) {
        Order order = findOrderById(id);
        order.setDestination(request.getDestination());
        return converter.convert(orderRepository.save(order));
    }

    public void deleteOrder(Long id) {
        findOrderById(id);
        orderRepository.deleteById(id);
    }

    public OrderCourierDto assignOrderToCourier(Long id,AssignOrderToCourierRequest request){
        Order order = findOrderById(id);
        courierService.changeCourierStatus(request.getCourierMail());

        order.setCourierMail(request.getCourierMail());
        order.setStatus(Status.IN_COURIER);
        return orderCourierDtoConverter.convert(orderRepository.save(order));
    }

    public List<OrderCourierDto> getAllAssignedOrderByCourierMail(String mail){
        courierService.findCourierByMail(mail);
        return orderCourierDtoConverter.convert(orderRepository.findAllByCourierMail(mail));
    }

    public OrderCourierDto changeOrderStatus(Long id,UpdateOrderStatusRequest request) {
        Order order = findOrderById(id);
        order.setStatus(Status.valueOf(request.getStatus()));
        return orderCourierDtoConverter.convert(orderRepository.save(order));
    }

    protected Order findOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(()->new OrderNotFoundException("Order couldn't be found by following id:"+id));

    }
}
