package com.company.parceldeliveryapp.repository;

import com.company.parceldeliveryapp.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CourierRepository extends JpaRepository<Courier,Long> {
    Optional<Courier> findByMail(String mail);
}
