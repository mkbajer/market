package com.solvd.market.service.impl;

import com.solvd.market.domain.shipments.Shipment;
import com.solvd.market.persistence.ShipmentRepository;
import com.solvd.market.persistence.impl.ShipmentMapperImpl;
import com.solvd.market.persistence.impl.ShipmentRepositoryImpl;
import com.solvd.market.service.ShipmentService;

import java.util.List;

public class ShipmentServiceImpl implements ShipmentService {
    private final ShipmentRepository shipmentRepository;

    public ShipmentServiceImpl() {
        //this.shipmentRepository = new ShipmentRepositoryImpl();
        this.shipmentRepository = new ShipmentMapperImpl();
    }

    @Override
    public Shipment create(Shipment shipment, Long orderId) {
        shipment.setId(null);
        shipmentRepository.create(shipment, orderId);
        return shipment;
    }

    @Override
    public List<Shipment> retrieveAll() {
        return shipmentRepository.findAll();
    }

    @Override
    public Shipment retrieveById(Long id) {
        return shipmentRepository.findById(id);
    }

    @Override
    public void update(Shipment shipment) {
        shipmentRepository.update(shipment);
    }

    @Override
    public void delete(Long id) {
        shipmentRepository.delete(id);
    }
}