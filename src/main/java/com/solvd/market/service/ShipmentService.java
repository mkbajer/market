package com.solvd.market.service;

import com.solvd.market.domain.shipments.Shipment;

import java.util.List;

public interface ShipmentService {

    Shipment create(Shipment shipment, Long orderId);

    List<Shipment> retrieveAll();

    Shipment retrieveById(Long id);

    void update(Shipment shipment);

    void delete(Long id);
}