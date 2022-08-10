package com.company.parceldeliveryapp.service;

import com.company.parceldeliveryapp.TestSupport;
import com.company.parceldeliveryapp.dto.CourierDto;
import com.company.parceldeliveryapp.dto.CreateCourierRequest;
import com.company.parceldeliveryapp.dto.converter.CourierDtoConverter;
import com.company.parceldeliveryapp.model.Courier;
import com.company.parceldeliveryapp.model.CourierStatus;
import com.company.parceldeliveryapp.repository.CourierRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


class CourierServiceTest extends TestSupport {
    private  CourierRepository courierRepository;
    private  CourierDtoConverter converter;

    private CourierService courierService;

    @BeforeEach
    public void setUp(){
        courierRepository = mock(CourierRepository.class);
        converter = mock(CourierDtoConverter.class);

        courierService = new CourierService(courierRepository,converter);
    }

    @Test
    void testCreateCourier_itShouldReturnCourierDto(){
        CreateCourierRequest request = new CreateCourierRequest(
                "alfa@gmail.com",
                "123",
                "Alfa",
                "Alfa",
                CourierStatus.FREE);
        Courier courier = generateCourier();
        Courier savedCourier =generateCourier();
        CourierDto courierDto = generateCourierDto();

        when(courierRepository.save(courier)).thenReturn(savedCourier);
        when(converter.convert(savedCourier)).thenReturn(courierDto);

        CourierDto result = courierService.createCourier(request);

        assertEquals(courierDto,result);

        verify(courierRepository).save(courier);
        verify(converter).convert(savedCourier);
    }

    @Test
    void testGetAllCouriers_itShouldReturnCourierDtoList(){
        List<Courier> courierList = generateCouriers();
        List<CourierDto> courierDtoList = generateCourierDtoList(courierList);

        when(courierRepository.findAll()).thenReturn(courierList);
        when(converter.convert(courierList)).thenReturn(courierDtoList);

        List<CourierDto> result = courierService.getAllCouriers();

        assertEquals(courierDtoList,result);

        verify(courierRepository).findAll();
        verify(converter).convert(courierList);

    }


}
