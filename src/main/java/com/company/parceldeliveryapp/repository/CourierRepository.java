package com.company.parceldeliveryapp.repository;

import com.company.parceldeliveryapp.model.Courier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourierRepository extends JpaRepository<Courier,Long> {
}
