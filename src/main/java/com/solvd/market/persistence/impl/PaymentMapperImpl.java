package com.solvd.market.persistence.impl;

import com.solvd.market.domain.payments.Payment;
import com.solvd.market.persistence.PaymentRepository;
import com.solvd.market.persistence.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class PaymentMapperImpl implements PaymentRepository {


    @Override
    public void create(Payment payment, Long orderId) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            PaymentRepository paymentRepository = session.getMapper(PaymentRepository.class);
            paymentRepository.create(payment, orderId);
        }
    }

    @Override
    public List<Payment> findAll() {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            PaymentRepository paymentRepository = session.getMapper(PaymentRepository.class);
            return paymentRepository.findAll();
        }
    }

    @Override
    public Payment findById(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            PaymentRepository paymentRepository = session.getMapper(PaymentRepository.class);
            return paymentRepository.findById(id);
        }
    }

    @Override
    public void update(Payment payment) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            PaymentRepository paymentRepository = session.getMapper(PaymentRepository.class);
            paymentRepository.update(payment);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            PaymentRepository paymentRepository = session.getMapper(PaymentRepository.class);
            paymentRepository.delete(id);
        }
    }
}