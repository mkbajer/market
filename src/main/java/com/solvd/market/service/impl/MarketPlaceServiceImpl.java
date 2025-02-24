package com.solvd.market.service.impl;

import com.solvd.market.domain.MarketPlace;
import com.solvd.market.persistence.MarketPlaceRepository;
import com.solvd.market.persistence.impl.MarketPlaceMapperImpl;
import com.solvd.market.service.MarketPlaceService;
import com.solvd.market.service.UserService;

import java.util.List;
import java.util.stream.Collectors;

public class MarketPlaceServiceImpl implements MarketPlaceService {
    private final MarketPlaceRepository marketPlaceRepository;
    private final UserService userService;

    public MarketPlaceServiceImpl() {
        // this.marketPlaceRepository = new MarketPlaceRepositoryImpl();
        this.marketPlaceRepository = new MarketPlaceMapperImpl();
        this.userService = new UserServiceImpl();
    }

    @Override
    public MarketPlace create(MarketPlace marketPlace) {
        marketPlace.setId(null);
        marketPlaceRepository.create(marketPlace);

        if (marketPlace.getUsers() != null) {
            marketPlace.setUsers(marketPlace.getUsers().stream()
                    .map(user -> userService.create(user))
                    .collect(Collectors.toList()));
        }
        return marketPlace;
    }

    @Override
    public List<MarketPlace> retrieveAll() {
        return marketPlaceRepository.findAll();
    }

    @Override
    public MarketPlace retrieveById(Long id) {
        return marketPlaceRepository.findById(id);
    }

    @Override
    public void update(MarketPlace marketPlace) {
        marketPlaceRepository.update(marketPlace);
    }

    @Override
    public void delete(Long id) {
        marketPlaceRepository.delete(id);
    }
}
