package com.solvd.market.persistence.impl;

import com.solvd.market.domain.shipments.Shipment;
import com.solvd.market.persistence.ShipmentRepository;
import com.solvd.market.persistence.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class ShipmentMapperImpl implements ShipmentRepository {

    @Override
    public void create(Shipment shipment, Long orderId) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            ShipmentRepository shipmentRepository = session.getMapper(ShipmentRepository.class);
            shipmentRepository.create(shipment, orderId);
        }
    }

    @Override
    public List<Shipment> findAll() {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            ShipmentRepository shipmentRepository = session.getMapper(ShipmentRepository.class);
            return shipmentRepository.findAll();
        }
    }

    @Override
    public Shipment findById(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            ShipmentRepository shipmentRepository = session.getMapper(ShipmentRepository.class);
            return shipmentRepository.findById(id);
        }
    }

    @Override
    public void update(Shipment shipment) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            ShipmentRepository shipmentRepository = session.getMapper(ShipmentRepository.class);
            shipmentRepository.update(shipment);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            ShipmentRepository shipmentRepository = session.getMapper(ShipmentRepository.class);
            shipmentRepository.delete(id);
        }
    }
}
