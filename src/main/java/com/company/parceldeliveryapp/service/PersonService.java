package com.company.parceldeliveryapp.service;

import com.company.parceldeliveryapp.dto.CreatePersonRequest;
import com.company.parceldeliveryapp.dto.PersonDto;
import com.company.parceldeliveryapp.dto.converter.PersonDtoConverter;
import com.company.parceldeliveryapp.exception.AccountAlreadyExistException;
import com.company.parceldeliveryapp.exception.CourierNotFoundException;
import com.company.parceldeliveryapp.exception.PersonNotFoundException;
import com.company.parceldeliveryapp.exception.UserNotFoundException;
import com.company.parceldeliveryapp.model.Person;
import com.company.parceldeliveryapp.model.Role;
import com.company.parceldeliveryapp.repository.PersonRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PersonService {
    private final PersonRepository personRepository;
    private final PersonDtoConverter converter;

    public PersonService(PersonRepository personRepository, PersonDtoConverter converter) {
        this.personRepository = personRepository;
        this.converter = converter;
    }

    public PersonDto createUser(CreatePersonRequest request) {
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

    public PersonDto createAdmin(CreatePersonRequest request) {
        findPersonByMail(request.getMail());
        Person person = new Person(
                request.getMail(),
                request.getPassword(),
                request.getFirstName(),
                request.getLastName(),
                Role.ADMIN
        );
        return converter.convert(personRepository.save(person));
    }

    private void findPersonByMail(String mail){
        Optional<Person> person =  personRepository.findByMail(mail);
        person.ifPresent(person1 -> {throw new AccountAlreadyExistException("Account already exist!");});
    }

    protected void checkUserIsExistOrNot(String mail){
         personRepository.findByMail(mail)
                .orElseThrow(()->new UserNotFoundException("User couldn't be found by following mail: " + mail));
    }

    protected void checkPersonIsCourierOrNot(String mail){
        Person person = checkPersonIsExistOrNot(mail);
        if(!person.getRole().equals(Role.COURIER)){
            throw new CourierNotFoundException("Courier couldn't be found by following mail:" + mail);
        }
    }
    private Person checkPersonIsExistOrNot(String mail){
       return personRepository.findByMail(mail)
                .orElseThrow(()->new PersonNotFoundException("Person couldn't be found by following mail: " + mail));
    }

}
