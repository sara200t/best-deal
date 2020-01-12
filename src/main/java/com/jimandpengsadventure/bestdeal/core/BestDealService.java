package com.jimandpengsadventure.bestdeal.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BestDealService {
    @Autowired
    private DealRepository dealRepository;

    public Optional<Deal> findDealById(UUID id) {
        return dealRepository.findById(id);
    }

    public List<Deal> findAll() {
        return dealRepository.findAll();
    }

    public Deal save(Deal deal) {
        return dealRepository.save(deal);
    }

    public void delete(UUID id) {
        dealRepository.deleteById(id);
    }
}
