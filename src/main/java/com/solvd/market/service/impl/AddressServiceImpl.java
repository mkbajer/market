package com.solvd.market.service.impl;

import com.solvd.market.domain.shipments.Address;
import com.solvd.market.persistence.AddressRepository;
import com.solvd.market.persistence.impl.AddressRepositoryImpl;
import com.solvd.market.service.AddressService;

import java.util.List;

public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    public AddressServiceImpl() {
        this.addressRepository = new AddressRepositoryImpl();
    }

    @Override
    public Address create(Address address, Long shipmentId) {
        address.setId(null);
        addressRepository.create(address, shipmentId);
        return address;
    }

    @Override
    public List<Address> retrieveAll() {
        return addressRepository.findAll();
    }

    @Override
    public Address retrieveById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public void update(Address address) {
        addressRepository.update(address);
    }

    @Override
    public void delete(Long id) {
        addressRepository.delete(id);
    }
}
