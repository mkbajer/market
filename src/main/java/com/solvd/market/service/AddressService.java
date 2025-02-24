package com.solvd.market.service;

import com.solvd.market.domain.shipments.Address;

import java.util.List;

public interface AddressService {
    Address create(Address address, Long shipmentId);

    List<Address> retrieveAll();

    Address retrieveById(Long id);

    void update(Address address);

    void delete(Long id);
}
