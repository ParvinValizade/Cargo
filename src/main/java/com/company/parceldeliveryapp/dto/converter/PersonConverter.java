package com.company.parceldeliveryapp.dto.converter;

import com.company.parceldeliveryapp.dto.PersonDto;
import com.company.parceldeliveryapp.dto.RoleDto;
import com.company.parceldeliveryapp.model.Person;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class PersonConverter {

    public PersonDto convert(Person from){
        return new PersonDto(
                from.getMail(),
                from.getFirstName(),
                from.getLastName(),
                RoleDto.valueOf(from.getRole().name()));
    }

    public List<PersonDto> convert(List<Person> fromList){
        return fromList.stream()
                .map(this::convert).collect(Collectors.toList());
    }
}
