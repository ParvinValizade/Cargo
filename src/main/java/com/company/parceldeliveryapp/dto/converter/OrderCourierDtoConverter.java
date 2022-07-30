package com.company.parceldeliveryapp.dto.converter;

import com.company.parceldeliveryapp.dto.OrderCourierDto;
import com.company.parceldeliveryapp.model.Order;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class OrderCourierDtoConverter {
    public OrderCourierDto convert(Order from){
        return new OrderCourierDto(
                from.getId(),
                from.getName(),
                from.getDestination(),
                from.getStatus(),
                from.getUserMail(),
                from.getCourierMail()
        );
    }

    public List<OrderCourierDto> convert(List<Order> fromList){
        return fromList.stream()
                .map(this::convert).collect(Collectors.toList());
    }
}
