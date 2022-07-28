package com.company.parceldeliveryapp.controller;

import com.company.parceldeliveryapp.dto.CreateUserRequest;
import com.company.parceldeliveryapp.dto.PersonDto;
import com.company.parceldeliveryapp.service.PersonService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/person")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @PostMapping("/createUser")
    public ResponseEntity<PersonDto> createUser(@RequestBody CreateUserRequest request){
         return ResponseEntity.ok(personService.createUser(request));
    }
}
