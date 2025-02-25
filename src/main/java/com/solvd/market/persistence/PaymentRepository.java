package com.solvd.market.persistence;

import com.solvd.market.domain.payments.Payment;

import java.util.List;

public interface PaymentRepository {

    void create(Payment payment, Long orderId);

    List<Payment> findAll();

    Payment findById(Long id);

    void update(Payment payment);

    void delete(Long id);
}