package com.solvd.market.persistence.impl;

import com.solvd.market.domain.MarketPlace;
import com.solvd.market.persistence.MarketPlaceRepository;
import com.solvd.market.persistence.MyBatisUtil;
import org.apache.ibatis.session.SqlSession;

import java.util.List;

public class MarketPlaceMapperImpl implements MarketPlaceRepository {

    @Override
    public void create(MarketPlace marketPlace) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            MarketPlaceRepository marketPlaceRepository = session.getMapper(MarketPlaceRepository.class);
            marketPlaceRepository.create(marketPlace);
        }
    }

    @Override
    public List<MarketPlace> findAll() {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            MarketPlaceRepository marketPlaceRepository = session.getMapper(MarketPlaceRepository.class);
            return marketPlaceRepository.findAll();
        }
    }

    @Override
    public MarketPlace findById(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            MarketPlaceRepository marketPlaceRepository = session.getMapper(MarketPlaceRepository.class);
            return marketPlaceRepository.findById(id);
        }
    }

    @Override
    public void update(MarketPlace marketPlace) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            MarketPlaceRepository marketPlaceRepository = session.getMapper(MarketPlaceRepository.class);
            marketPlaceRepository.update(marketPlace);
        }
    }

    @Override
    public void delete(Long id) {
        try (SqlSession session = MyBatisUtil.getSessionFactory().openSession(true)) {
            MarketPlaceRepository marketPlaceRepository = session.getMapper(MarketPlaceRepository.class);
            marketPlaceRepository.delete(id);
        }
    }
}