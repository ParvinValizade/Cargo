package com.company.parceldeliveryapp.service;

import com.company.parceldeliveryapp.dto.CreateOrderRequest;
import com.company.parceldeliveryapp.dto.OrderCourierDto;
import com.company.parceldeliveryapp.dto.OrderDto;
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

    public OrderService(OrderRepository orderRepository, OrderDtoConverter converter, OrderCourierDtoConverter orderCourierDtoConverter, PersonService personService) {
        this.orderRepository = orderRepository;
        this.converter = converter;
        this.orderCourierDtoConverter = orderCourierDtoConverter;
        this.personService = personService;
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

    public OrderDto updateOrderDestination(Long id, String destination) {
        Order order = findOrderById(id);
        order.setDestination(destination);
        return converter.convert(orderRepository.save(order));
    }

    public void deleteOrder(Long id) {
        findOrderById(id);
        orderRepository.deleteById(id);
    }

    public OrderCourierDto assignOrderToCourier(Long orderId,String courierMail){
        Order order = findOrderById(orderId);
        personService.checkPersonIsCourierOrNot(courierMail);
        order.setCourierMail(courierMail);
        return orderCourierDtoConverter.convert(orderRepository.save(order));
    }

    public OrderCourierDto changeOrderStatusById(Long id, String status) {
        Order order = findOrderById(id);
        order.setStatus(Status.valueOf(status));
        return orderCourierDtoConverter.convert(orderRepository.save(order));
    }

    protected Order findOrderById(Long id){
        return orderRepository.findById(id)
                .orElseThrow(()->new OrderNotFoundException("Order couldn't be found by following id:"+id));

    }
}
