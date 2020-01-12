package com.jimandpengsadventure.bestdeal.core.service;

import com.jimandpengsadventure.bestdeal.core.entity.Deal;
import com.jimandpengsadventure.bestdeal.core.repository.DealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class BestDealService {
    @Autowired
    private DealRepository dealRepository;

    public Deal getDeal(UUID id) {
        return dealRepository.getOne(id);
    }

    public List<Deal> findAll() {
        return dealRepository.findAll();
    }
}
