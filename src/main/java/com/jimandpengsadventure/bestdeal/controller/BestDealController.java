package com.jimandpengsadventure.bestdeal.controller;

import com.jimandpengsadventure.bestdeal.api.BestDealDto;
import com.jimandpengsadventure.bestdeal.core.entity.Deal;
import com.jimandpengsadventure.bestdeal.core.service.BestDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

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
        return bestDealService.findDealById(id)
                .map(this::dealToDto)
                .orElseThrow(() -> new BestDealNotFoundException(id));
    }

    @GetMapping()
    public List<BestDealDto> findAll() {
        return bestDealService.findAll().stream()
                .map(this::dealToDto)
                .collect(Collectors.toList());
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public BestDealDto create(@RequestBody BestDealDto dealDto) {
        return dealToDto(bestDealService.save(dtoToDeal(dealDto)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update(@RequestBody BestDealDto dealDto, @PathVariable("id") UUID id) {
        dealDto.setId(id);
        bestDealService.findDealById(id)
                .map(deal -> dtoToExistingDeal(deal, dealDto))
                .map(bestDealService::save)
                .orElseThrow(() -> new BestDealNotFoundException(id));
    }

    private BestDealDto dealToDto(Deal deal) {
        return new BestDealDto()
                .setId(deal.getId())
                .setItem(deal.getItem())
                .setWalmartPrice(deal.getWalmartPrice())
                .setCostcoPrice(deal.getCostcoPrice());
    }

    private Deal dtoToDeal(BestDealDto dealDto) {
        return dtoToExistingDeal(Deal.newInstance(), dealDto);
    }

    private Deal dtoToExistingDeal(Deal deal, BestDealDto dealDto) {
        return deal
                .setItem(dealDto.getItem())
                .setWalmartPrice(dealDto.getWalmartPrice())
                .setCostcoPrice(dealDto.getCostcoPrice());
    }
}
