package com.jimandpengsadventure.bestdeal;

import com.jimandpengsadventure.bestdeal.core.BestDealService;
import com.jimandpengsadventure.bestdeal.core.Deal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;

@SpringBootApplication
public class BestDealApplication {

	@Autowired
	private BestDealService bestDealService;

	@PostConstruct
	public void createSeedData() {
		create("Samsung TV", new BigDecimal("50.00"), new BigDecimal("40.01"));
		create("LG TV", new BigDecimal("55.00"), new BigDecimal("40.02"));
		create("Potato", new BigDecimal(".50"), new BigDecimal(".28"));
	}

	private void create(String item, BigDecimal walmart, BigDecimal costco) {
		bestDealService.save(Deal.newInstance()
				.setItem(item)
				.setWalmartPrice(walmart)
				.setCostcoPrice(costco));
	}

	public static void main(String[] args) {
		SpringApplication.run(BestDealApplication.class, args);
	}

}
