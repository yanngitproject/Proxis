package com.proxis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.proxis.entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

}
