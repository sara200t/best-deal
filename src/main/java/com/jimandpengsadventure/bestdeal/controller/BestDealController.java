package com.jimandpengsadventure.bestdeal.controller;

import com.jimandpengsadventure.bestdeal.api.BestDealDto;
import com.jimandpengsadventure.bestdeal.core.entity.Deal;
import com.jimandpengsadventure.bestdeal.core.service.BestDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/best-deals")
public class BestDealController {

    @Autowired
    private BestDealService bestDealService;

    @GetMapping("/{id}")
    public BestDealDto getBestDeal(@PathVariable("id") UUID id) {
        return dealToDto(bestDealService.getDeal(id));
    }

    @GetMapping()
    public List<BestDealDto> findAll() {
        return bestDealService.findAll().stream()
                .map(this::dealToDto)
                .collect(Collectors.toList());
    }

    private BestDealDto dealToDto(Deal deal) {
        return new BestDealDto()
                .setId(deal.getId())
                .setItem(deal.getItem())
                .setWalmartPrice(deal.getWalmartPrice())
                .setCostcoPrice(deal.getCostcoPrice());
    }
}
