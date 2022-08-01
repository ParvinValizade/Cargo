package com.company.parceldeliveryapp.repository;

import com.company.parceldeliveryapp.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order,Long> {
    List<Order> findAllByUserMail(String mail);

    List<Order> findAllByCourierMail(String mail);
}
