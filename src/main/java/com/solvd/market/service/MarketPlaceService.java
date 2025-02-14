package com.solvd.market.service;

import com.solvd.market.domain.MarketPlace;

import java.util.List;

public interface MarketPlaceService {

    MarketPlace create(MarketPlace marketPlace);

    List<MarketPlace> retrieveAll();

    MarketPlace retrieveById(Long id);

    void update(MarketPlace marketPlace);

    void delete(Long id);

}
