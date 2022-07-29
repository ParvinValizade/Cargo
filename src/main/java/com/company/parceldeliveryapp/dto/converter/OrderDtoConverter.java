package com.company.parceldeliveryapp.dto.converter;

import com.company.parceldeliveryapp.dto.OrderDto;
import com.company.parceldeliveryapp.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderDtoConverter {

    public OrderDto convert(Order from){
        return new OrderDto(from.getId(),
                from.getName(),
                from.getDestination(),
                from.getStatus(),
                from.getUserMail());
    }

    public List<OrderDto> convert(List<Order> fromList){
        return fromList.stream()
                .map(this::convert).collect(Collectors.toList());
    }
}
