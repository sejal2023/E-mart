package com.example.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Models.PaymentDetails;

import java.util.Optional;

@Repository
public interface PaymentRepository extends JpaRepository<PaymentDetails, Integer> {
    Optional<PaymentDetails> findByOrder_Id(int orderID);
}


