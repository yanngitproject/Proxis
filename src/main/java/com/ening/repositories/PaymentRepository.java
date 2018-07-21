package com.ening.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ening.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
