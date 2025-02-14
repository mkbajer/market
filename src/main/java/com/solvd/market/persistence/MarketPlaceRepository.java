package com.solvd.market.persistence;

import com.solvd.market.domain.MarketPlace;

import java.util.List;

public interface MarketPlaceRepository {

    void create(MarketPlace marketPlace);

    List<MarketPlace> findAll();

    MarketPlace findById(Long id);

    void update(MarketPlace marketPlace);

    void delete(Long id);
}

