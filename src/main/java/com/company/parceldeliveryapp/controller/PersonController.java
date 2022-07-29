package com.company.parceldeliveryapp.controller;

import com.company.parceldeliveryapp.dto.CreatePersonRequest;
import com.company.parceldeliveryapp.dto.PersonDto;
import com.company.parceldeliveryapp.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<PersonDto> createUser(@RequestBody CreatePersonRequest request){
         return ResponseEntity.ok(personService.createUser(request));
    }
    @PostMapping("/createAdmin")
    public ResponseEntity<PersonDto> createAdmin(@RequestBody CreatePersonRequest request){
        return ResponseEntity.ok(personService.createAdmin(request));
    }

    @PostMapping("/createCourier")
    public ResponseEntity<PersonDto> createCourier(@RequestBody CreatePersonRequest request){
        return ResponseEntity.ok(personService.createCourier(request));
    }
}
