package com.company.parceldeliveryapp.service;

import com.company.parceldeliveryapp.dto.CreateUserRequest;
import com.company.parceldeliveryapp.dto.PersonDto;
import com.company.parceldeliveryapp.dto.converter.PersonConverter;
import com.company.parceldeliveryapp.exception.UserAlreadyExistException;
import com.company.parceldeliveryapp.model.Person;
import com.company.parceldeliveryapp.model.Role;
import com.company.parceldeliveryapp.repository.PersonRepository;
import org.springframework.stereotype.Service;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonConverter converter;

    public PersonService(PersonRepository personRepository, PersonConverter converter) {
        this.personRepository = personRepository;
        this.converter = converter;
    }

    public PersonDto createUser(CreateUserRequest request) {
        findPersonByMail(request.getMail());
        Person person = new Person(
                request.getMail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                Role.USER
        );
        return converter.convert(personRepository.save(person));
    }

    private Person findPersonByMail(String mail){
        return personRepository.findByMail(mail)
                .orElseThrow(()->new UserAlreadyExistException("User already exists"));
    }
}
