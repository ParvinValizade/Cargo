package com.company.parceldeliveryapp.service;

import com.company.parceldeliveryapp.dto.CreateOrderRequest;
import com.company.parceldeliveryapp.dto.OrderDto;
import com.company.parceldeliveryapp.dto.converter.OrderDtoConverter;
import com.company.parceldeliveryapp.model.Order;
import com.company.parceldeliveryapp.model.Status;
import com.company.parceldeliveryapp.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final OrderDtoConverter converter;

    private final PersonService personService;

    public OrderService(OrderRepository orderRepository, OrderDtoConverter converter, PersonService personService) {
        this.orderRepository = orderRepository;
        this.converter = converter;
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
}
