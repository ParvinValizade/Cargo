package com.company.parceldeliveryapp.service;

import com.company.parceldeliveryapp.dto.CourierDto;
import com.company.parceldeliveryapp.dto.CreateCourierRequest;
import com.company.parceldeliveryapp.dto.converter.CourierDtoConverter;
import com.company.parceldeliveryapp.exception.CourierNotFoundException;
import com.company.parceldeliveryapp.model.Courier;
import com.company.parceldeliveryapp.model.CourierStatus;
import com.company.parceldeliveryapp.model.Role;
import com.company.parceldeliveryapp.repository.CourierRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourierService {
    private final CourierRepository courierRepository;
    private final CourierDtoConverter converter;

    public CourierService(CourierRepository courierRepository, CourierDtoConverter converter) {
        this.courierRepository = courierRepository;
        this.converter = converter;
    }

    public CourierDto createCourier(CreateCourierRequest request) {
        Courier courier = new Courier(
                request.getMail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                Role.COURIER,
                request.getStatus()
        );
        return converter.convert(courierRepository.save(courier));
    }

    protected void changeCourierStatus(String mail){
        Courier courier = findCourierByMail(mail);
        courier.setStatus(CourierStatus.OCCUPIED);
        converter.convert(courierRepository.save(courier));
    }

    public List<CourierDto> getAllCouriers() {
        return converter.convert(courierRepository.findAll());
    }

    protected Courier findCourierByMail(String mail){
        return courierRepository.findByMail(mail)
                .orElseThrow(()->new CourierNotFoundException("Courier couldn't be found by following mail:" + mail));
    }

}
