package com.company.parceldeliveryapp.dto.converter;

import com.company.parceldeliveryapp.dto.CourierDto;
import com.company.parceldeliveryapp.dto.RoleDto;
import com.company.parceldeliveryapp.model.Courier;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CourierDtoConverter {
    public CourierDto convert(Courier from){
        return new CourierDto(
                from.getMail(),
                from.getFirstName(),
                from.getLastName(),
                RoleDto.valueOf(from.getRole().name()),
                from.getStatus());
    }

    public List<CourierDto> convert(List<Courier> fromList){
        return fromList.stream()
                .map(this::convert).collect(Collectors.toList());
    }
}
