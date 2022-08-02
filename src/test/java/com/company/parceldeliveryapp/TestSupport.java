package com.company.parceldeliveryapp;

import com.company.parceldeliveryapp.dto.OrderDto;
import com.company.parceldeliveryapp.model.Order;
import com.company.parceldeliveryapp.model.Status;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {

   public static List<Order> generateOrders(){
       return IntStream.range(0, 5).mapToObj(i ->
               new Order((long) i,
                       i+"Phone",
                       "Quba"+i,
                       Status.ORDERED,
                       "parvin.valizade@mail.ru",
                       null)).collect(Collectors.toList());
   }

   public static List<OrderDto> generateOrderDtoList(List<Order> orderList){
       return orderList.stream()
               .map(from->new OrderDto(
                       from.getId(),
                       from.getName(),
                       from.getDestination(),
                       from.getStatus(),
                       from.getUserMail()
               )).collect(Collectors.toList());
   }
}
