package com.solvd.market.service.impl;

import com.solvd.market.domain.payments.Payment;
import com.solvd.market.persistence.PaymentRepository;
import com.solvd.market.persistence.impl.PaymentRepositoryImpl;
import com.solvd.market.service.PaymentService;

import java.util.List;

public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl() {
        //this.paymentRepository = new PaymentRepositoryImpl();
        this.paymentRepository = new PaymentRepositoryImpl();
    }

    @Override
    public Payment create(Payment payment, Long orderId) {
        payment.setId(null);
        paymentRepository.create(payment, orderId);
        return payment;
    }

    @Override
    public List<Payment> retrieveAll() {
        return paymentRepository.findAll();
    }

    @Override
    public Payment retrieveById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public void update(Payment payment) {
        paymentRepository.update(payment);
    }

    @Override
    public void delete(Long id) {
        paymentRepository.delete(id);
    }
}