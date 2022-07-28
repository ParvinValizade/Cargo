package com.company.parceldeliveryapp.repository;

import com.company.parceldeliveryapp.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PersonRepository extends JpaRepository<Person,Long> {
    Optional<Person> findByMail(String mail);
}
