package com.jimandpengsadventure.bestdeal.controller;

import com.jimandpengsadventure.bestdeal.api.BestDealDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class BestDealControllerTest {

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	void testFindAll_NoDeals_ExpectEmptyList() {
		ResponseEntity<BestDealDto[]> responseEntity = testRestTemplate.getForEntity("/api/best-deals", BestDealDto[].class);
		assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertEquals(0, responseEntity.getBody().length);
	}

	@Test
	void testCreateDeal_ValidDeal_ExpectCreated() {
		BestDealDto bestDealDto = new BestDealDto()
				.setItem("Apple Watch")
				.setWalmartPrice(new BigDecimal("300.50"))
				.setCostcoPrice(new BigDecimal("250.00"));

		ResponseEntity<BestDealDto> responseEntity = testRestTemplate.postForEntity("/api/best-deals", bestDealDto, BestDealDto.class);
		assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
		assertNotNull(responseEntity.getBody());
		assertAll(
				() -> assertNotNull(responseEntity.getBody().getId(), "id"),
				() -> assertEquals("Apple Watch", responseEntity.getBody().getItem(), "item"),
				() -> assertEquals(new BigDecimal("300.50"), responseEntity.getBody().getWalmartPrice(), "walmart"),
				() -> assertEquals(new BigDecimal("250.00"), responseEntity.getBody().getCostcoPrice(), "costco")
		);
	}



}