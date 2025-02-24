package com.solvd.market.persistence;

import com.solvd.market.domain.shipments.Address;

import java.util.List;

public interface AddressRepository {
    void create(Address address, Long shipmentId);

    List<Address> findAll();

    Address findById(Long id);

    void update(Address address);

    void delete(Long id);
}
