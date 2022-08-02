package com.company.parceldeliveryapp.service;

import com.company.parceldeliveryapp.TestSupport;
import com.company.parceldeliveryapp.dto.CreateOrderRequest;
import com.company.parceldeliveryapp.dto.OrderDto;
import com.company.parceldeliveryapp.dto.converter.OrderCourierDtoConverter;
import com.company.parceldeliveryapp.dto.converter.OrderDtoConverter;
import com.company.parceldeliveryapp.exception.UserNotFoundException;
import com.company.parceldeliveryapp.model.Order;
import com.company.parceldeliveryapp.model.Person;
import com.company.parceldeliveryapp.model.Role;
import com.company.parceldeliveryapp.model.Status;
import com.company.parceldeliveryapp.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        Order order = new Order("Etir","Elmler Akademiyasi", Status.ORDERED,"parvin.valizade@mail.ru",null);
        Order savedOrder = new Order("Etir","Elmler Akademiyasi", Status.ORDERED,"parvin.valizade@mail.ru",null);
        OrderDto orderDto = new OrderDto(6l,"Etir","Elmler Akademiyasi", Status.ORDERED,"parvin.valizade@mail.ru");

        when(personService.checkUserIsExistOrNot(request.getUserMail())).thenReturn(person);
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

        when(personService.checkUserIsExistOrNot(request.getUserMail())).thenThrow(UserNotFoundException.class);

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

        List<OrderDto> result = orderService.getAllOrders(mail);

        assertEquals(orderDtoList,result);

        verify(orderRepository).findAllByUserMail(mail);
        verify(converter).convert(orderList);
    }


}
