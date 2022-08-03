package com.company.parceldeliveryapp.service;

import com.company.parceldeliveryapp.TestSupport;
import com.company.parceldeliveryapp.dto.*;
import com.company.parceldeliveryapp.dto.converter.OrderCourierDtoConverter;
import com.company.parceldeliveryapp.dto.converter.OrderDtoConverter;
import com.company.parceldeliveryapp.exception.CourierNotFoundException;
import com.company.parceldeliveryapp.exception.OrderNotFoundException;
import com.company.parceldeliveryapp.exception.UserNotFoundException;
import com.company.parceldeliveryapp.model.*;
import com.company.parceldeliveryapp.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

 class OrderServiceTest extends TestSupport {

    private  OrderRepository orderRepository;
    private  OrderDtoConverter converter;
    private  OrderCourierDtoConverter orderCourierDtoConverter;

    private  PersonService personService;
    private  CourierService courierService;

    private OrderService orderService;

    @BeforeEach
    public void setUp(){
        orderRepository = mock(OrderRepository.class);
        converter = mock(OrderDtoConverter.class);
        orderCourierDtoConverter = mock(OrderCourierDtoConverter.class);
        personService = mock(PersonService.class);
        courierService = mock(CourierService.class);

        orderService = new OrderService(orderRepository,converter,orderCourierDtoConverter,personService,courierService);

    }

    @Test
    void testCreateOrder_whenUserMailExist_itShouldReturnOrderDto(){
        CreateOrderRequest request = new CreateOrderRequest("Etir","Elmler Akademiyasi","parvin.valizade@mail.ru");
        Person person = new Person("parvin.valizade@mail.ru","1234","Parvin","Valizade", Role.USER);
        Order order = generateOrder();
        Order savedOrder = generateOrder();
        OrderDto orderDto = generateOrderDto();

        doNothing().when(personService).checkUserIsExistOrNot(request.getUserMail());
        when(orderRepository.save(order)).thenReturn(savedOrder);
        when(converter.convert(savedOrder)).thenReturn(orderDto);

        OrderDto result = orderService.createOrder(request);

        assertEquals(orderDto,result);

        verify(personService).checkUserIsExistOrNot(request.getUserMail());
        verify(orderRepository).save(order);
        verify(converter).convert(savedOrder);
    }

    @Test
    void testCreateOrder_whenUserMailDoesNotExist_itShouldThrowUserNotFoundException(){
        CreateOrderRequest request = new CreateOrderRequest("Etir","Elmler Akademiyasi","parvin.valizade@mail.ru");

        doThrow(UserNotFoundException.class).when(personService).checkUserIsExistOrNot(request.getUserMail());

        assertThrows(UserNotFoundException.class,
                ()-> orderService.createOrder(request));

        verify(personService).checkUserIsExistOrNot(request.getUserMail());
        verifyNoInteractions(orderRepository);
        verifyNoInteractions(converter);
    }

    @Test
    void testGetAllOrders_itShouldReturnOrderDtoList(){
        List<Order> orderList = generateOrders();
        List<OrderDto> orderDtoList = generateOrderDtoList(orderList);

        when(orderRepository.findAll()).thenReturn(orderList);
        when(converter.convert(orderList)).thenReturn(orderDtoList);

        List<OrderDto> result = orderService.getAllOrders();

        assertEquals(orderDtoList,result);

        verify(orderRepository).findAll();
        verify(converter).convert(orderList);
    }

    @Test
    void testGetAllOrdersByUserMail_itShouldReturnOrderDtoList(){
        String mail = "parvin.valizade@mail.ru";
        List<Order> orderList = generateOrders();
        List<OrderDto> orderDtoList = generateOrderDtoList(orderList);

        when(orderRepository.findAllByUserMail(mail)).thenReturn(orderList);
        when(converter.convert(orderList)).thenReturn(orderDtoList);

        List<OrderDto> result = orderService.getAllOrdersByUserMail(mail);

        assertEquals(orderDtoList,result);

        verify(orderRepository).findAllByUserMail(mail);
        verify(converter).convert(orderList);
    }

    @Test
     void testGetOrderById_whenOrderIdExist_itShouldReturnOrderDto(){
        Long orderId = 6L;
        Order order = generateOrder();
        OrderDto orderDto = generateOrderDto();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(converter.convert(order)).thenReturn(orderDto);

        OrderDto result = orderService.getOrderById(orderId);

        assertEquals(orderDto,result);

        verify(orderRepository).findById(orderId);
        verify(converter).convert(order);

    }

     @Test
     void testGetOrderById_whenOrderIdDoesNotExist_itShouldThrowOrderNotFoundException(){
         Long orderId = 6L;

         when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

         assertThrows(OrderNotFoundException.class,()->
                orderService.getOrderById(orderId));

         verify(orderRepository).findById(orderId);
         verifyNoInteractions(converter);

     }

     @Test
     void testUpdateOrderDestination_whenOrderIdExist_itShouldReturnOrderDto(){
         Long orderId = 6L;
         UpdateOrderDestinationRequest request = new UpdateOrderDestinationRequest("Elmler Akademiyasi");
         Order order = generateOrder();
         Order savedOrder = generateOrder();
         OrderDto orderDto = generateOrderDto();

         when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
         when(orderRepository.save(order)).thenReturn(savedOrder);
         when(converter.convert(savedOrder)).thenReturn(orderDto);

         OrderDto result = orderService.updateOrderDestination(orderId,request);

         assertEquals(orderDto,result);

         verify(orderRepository).findById(orderId);
         verify(orderRepository).save(order);
         verify(converter).convert(savedOrder);

     }

     @Test
     void testUpdateOrderDestination_whenOrderIdDoesNotExist_itShouldThrowOrderNotFoundException(){
         Long orderId = 6L;
         UpdateOrderDestinationRequest request = new UpdateOrderDestinationRequest("Elmler Akademiyasi");

         when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

         assertThrows(OrderNotFoundException.class,()->
                 orderService.updateOrderDestination(orderId,request));

         verify(orderRepository).findById(orderId);
         verifyNoMoreInteractions(orderRepository);
         verifyNoInteractions(converter);
     }

     @Test
     void testDeleteOrder_whenOrderIdExist_itShouldDeleteOrder(){
        Long orderId = 6L;
        Order order = generateOrder();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));

        orderService.deleteOrder(orderId);

        verify(orderRepository).findById(orderId);
        verify(orderRepository).deleteById(orderId);

     }

     @Test
     void testDeleteOrder_whenOrderIdDoesNotExist_itShouldThrowOrderNotFoundException(){
         Long orderId = 6L;

         when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

         assertThrows(OrderNotFoundException.class,()->
                 orderService.deleteOrder(orderId));

         verify(orderRepository).findById(orderId);
         verifyNoMoreInteractions(orderRepository);

     }

     @Test
     void testAssignOrderToCourier_whenOrderIdExistAndCourierExist_itShouldReturnOrderCourierDto(){
         Long orderId = 6L;
         Courier courier = new Courier("tahir@gmail.com","123","Tahir","Mahirov",Role.COURIER, CourierStatus.FREE);
         AssignOrderToCourierRequest request = new AssignOrderToCourierRequest("tahir@gmail.com");
         Order order = generateOrder();
         Order savedOrder = generateOrder();
         OrderCourierDto orderCourierDto = generateOrderCourierDto();

         when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
         doNothing().when(courierService).changeCourierStatus(request.getCourierMail());
         when(orderRepository.save(order)).thenReturn(savedOrder);
         when(orderCourierDtoConverter.convert(savedOrder)).thenReturn(orderCourierDto);

         OrderCourierDto result = orderService.assignOrderToCourier(orderId,request);

         assertEquals(orderCourierDto,result);

         verify(orderRepository).findById(orderId);
         verify(courierService).changeCourierStatus(request.getCourierMail());
         verify(orderRepository).save(order);
         verify(orderCourierDtoConverter).convert(savedOrder);
     }


     @Test
     void testAssignOrderToCourier_whenOrderIdDoesNotExistButCourierExist_itShouldTrowOrderNotFoundException(){
         Long orderId = 6L;
        AssignOrderToCourierRequest request = new AssignOrderToCourierRequest("tahir@gmail.com");


         when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

         assertThrows(OrderNotFoundException.class,()->
                 orderService.assignOrderToCourier(orderId,request));

         verify(orderRepository).findById(orderId);
         verifyNoInteractions(courierService);
         verifyNoInteractions(orderCourierDtoConverter);
         verifyNoMoreInteractions(orderRepository);

     }

     @Test
     void testAssignOrderToCourier_whenOrderIdExistButCourierDoesNotExist_itShouldThrowCourierNotFoundException(){
         Long orderId = 6L;
         AssignOrderToCourierRequest request = new AssignOrderToCourierRequest("tahir@gmail.com");
         Order order = generateOrder();


         when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
         doThrow(CourierNotFoundException.class).when(courierService).changeCourierStatus(request.getCourierMail());


         assertThrows(CourierNotFoundException.class,()->
                 orderService.assignOrderToCourier(orderId,request));

         verify(orderRepository).findById(orderId);
         verify(courierService).changeCourierStatus(request.getCourierMail());
         verifyNoMoreInteractions(orderRepository);
         verifyNoInteractions(orderCourierDtoConverter);
     }

     @Test
     void testGetAllAssignedOrderByCourierMail_whenCourierMailExist_itShouldReturnOrderCourierDtoList(){
        String mail = "tahir@gmail.com";
        Courier courier = new Courier("tahir@gmail.com","123","Tahir","Mahirov",Role.COURIER,CourierStatus.FREE);
        List<Order> orderList = generateOrders();
        List<OrderCourierDto> orderCourierDtoList = generateOrderCourierDtoList(orderList);

        when(courierService.findCourierByMail(mail)).thenReturn(courier);
        when(orderRepository.findAllByCourierMail(mail)).thenReturn(orderList);
        when(orderCourierDtoConverter.convert(orderList)).thenReturn(orderCourierDtoList);

        List<OrderCourierDto> result = orderService.getAllAssignedOrderByCourierMail(mail);

        assertEquals(orderCourierDtoList,result);

        verify(courierService).findCourierByMail(mail);
        verify(orderRepository).findAllByCourierMail(mail);
        verify(orderCourierDtoConverter).convert(orderList);

     }

     @Test
     void testGetAllAssignedOrderByCourierMail_whenCourierMailDoesNotExist_itShouldThrowCourierNotFoundException(){
         String mail = "tahir@gmail.com";

         when(courierService.findCourierByMail(mail)).thenThrow(CourierNotFoundException.class);

         assertThrows(CourierNotFoundException.class,
                 ()-> orderService.getAllAssignedOrderByCourierMail(mail));

         verify(courierService).findCourierByMail(mail);
         verifyNoInteractions(orderRepository);
         verifyNoInteractions(orderCourierDtoConverter);

     }

     @Test
     void testChangeOrderStatus_whenOrderIdExist_itShouldOrderCourierDto(){
        Long orderId = 6L;
        UpdateOrderStatusRequest request = new UpdateOrderStatusRequest("DELIVERED");
        Order order = generateOrder();
        Order savedOrder = generateOrder();
        OrderCourierDto orderCourierDto = generateOrderCourierDto();

        when(orderRepository.findById(orderId)).thenReturn(Optional.of(order));
        when(orderRepository.save(order)).thenReturn(savedOrder);
        when(orderCourierDtoConverter.convert(savedOrder)).thenReturn(orderCourierDto);

        OrderCourierDto result = orderService.changeOrderStatus(orderId,request);

        assertEquals(orderCourierDto,result);

        verify(orderRepository).findById(orderId);
        verify(orderRepository).save(order);
        verify(orderCourierDtoConverter).convert(savedOrder);

     }

     @Test
     void testChangeOrderStatus_whenOrderIdDoesNotExist_itShouldThrowOrderNotFoundException(){
         Long orderId = 6L;
         UpdateOrderStatusRequest request = new UpdateOrderStatusRequest("DELIVERED");


         when(orderRepository.findById(orderId)).thenReturn(Optional.empty());

         assertThrows(OrderNotFoundException.class,()->
                 orderService.changeOrderStatus(orderId,request));

         verify(orderRepository).findById(orderId);
         verifyNoMoreInteractions(orderRepository);
         verifyNoInteractions(orderCourierDtoConverter);

     }



}
