package com.jimandpengsadventure.bestdeal.controller;

import com.jimandpengsadventure.bestdeal.api.BestDealDto;
import com.jimandpengsadventure.bestdeal.core.service.BestDealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/best-deals")
public class BestDealController {

	@Autowired
	private BestDealService bestDealService;

	@GetMapping("/{id}")
	public BestDealDto getBestDeal(@PathVariable("id") UUID id) {

	}
}
