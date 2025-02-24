package com.solvd.market.service;

import com.solvd.market.domain.payments.Payment;

import java.util.List;

public interface PaymentService {
    Payment create(Payment payment, Long orderId);

    List<Payment> retrieveAll();

    Payment retrieveById(Long id);

    void update(Payment payment);

    void delete(Long id);
}