package com.company.parceldeliveryapp;

import com.company.parceldeliveryapp.dto.CourierDto;
import com.company.parceldeliveryapp.dto.OrderCourierDto;
import com.company.parceldeliveryapp.dto.OrderDto;
import com.company.parceldeliveryapp.dto.RoleDto;
import com.company.parceldeliveryapp.model.*;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {

    /* For OrderService*/
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

   public static Order generateOrder(){
       return new Order("Etir",
               "Elmler Akademiyasi",
               Status.ORDERED,
               "parvin.valizade@mail.ru",
               null);
   }

   public static OrderDto generateOrderDto(){
       return new OrderDto(6L,
               "Etir",
               "Elmler Akademiyasi",
               Status.ORDERED,
               "parvin.valizade@mail.ru");
   }

   public static OrderCourierDto generateOrderCourierDto(){
       return new OrderCourierDto(6L,
               "Etir",
               "Elmler Akademiyasi",
               Status.ORDERED,
               "parvin.valizade@mail.ru",
               "tahir@gmail.com");
   }

    public static List<OrderCourierDto> generateOrderCourierDtoList(List<Order> orderList){
        return orderList.stream()
                .map(from-> new OrderCourierDto(
                        from.getId(),
                        from.getName(),
                        from.getDestination(),
                        from.getStatus(),
                        from.getUserMail(),
                        "tahir@gmail.com"
                )).collect(Collectors.toList());
    }

    /* For CourierService*/
    public static Courier generateCourier(){
        return new Courier("alfa@gmail.com",
                "123",
                "Alfa",
                "Alfa",
                Role.COURIER,
                CourierStatus.FREE);
    }

    public static CourierDto generateCourierDto(){
        return new CourierDto("alfa@gmail.com",
                "Alfa",
                "Alfa",
                RoleDto.valueOf(Role.COURIER.name()),
                CourierStatus.FREE);
    }

    public static List<Courier> generateCouriers(){
        return IntStream.range(0, 5).mapToObj(i ->
                new Courier(
                        i+"alfa@gmail.com",
                        "123",
                        i+"Alfa",
                        i+"Alfa",
                        Role.COURIER,
                        CourierStatus.FREE)).collect(Collectors.toList());
    }

    public static List<CourierDto> generateCourierDtoList(List<Courier> courierList){
        return courierList.stream()
                .map(from->new CourierDto(
                        from.getMail(),
                        from.getFirstName(),
                        from.getLastName(),
                        RoleDto.valueOf(from.getRole().name()),
                        from.getStatus()
                )).collect(Collectors.toList());
    }
}
