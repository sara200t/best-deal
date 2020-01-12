package com.jimandpengsadventure.bestdeal.controller;

import com.jimandpengsadventure.bestdeal.api.BestDealDto;
import org.hibernate.annotations.SQLDeleteAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;

import java.math.BigDecimal;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@Sql("classpath:clean-up.sql")
@SpringBootTest(webEnvironment = RANDOM_PORT)
class BestDealControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    void testFindDeal_NotExist_ExpectNotFound() {
        ResponseEntity<BestDealDto> responseEntity = testRestTemplate.getForEntity("/api/best-deals/" + UUID.randomUUID(), BestDealDto.class);
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testFindDeal_Exist_ExpectFound() {
        BestDealDto bestDealDto = new BestDealDto()
                .setItem("Apple Watch")
                .setWalmartPrice(new BigDecimal("300.50"))
                .setCostcoPrice(new BigDecimal("250.00"));

        BestDealDto createdDealDto = testRestTemplate.postForObject("/api/best-deals", bestDealDto, BestDealDto.class);

        ResponseEntity<BestDealDto> responseEntity = testRestTemplate.getForEntity("/api/best-deals/" + createdDealDto.getId(), BestDealDto.class);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertAll(
                () -> assertEquals(createdDealDto.getId(), responseEntity.getBody().getId()),
                () -> assertEquals(createdDealDto.getItem(), responseEntity.getBody().getItem()),
                () -> assertEquals(createdDealDto.getWalmartPrice(), responseEntity.getBody().getWalmartPrice()),
                () -> assertEquals(createdDealDto.getCostcoPrice(), responseEntity.getBody().getCostcoPrice())
        );
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

    @Test
    void testUpdateDeal_ChangeItemName_ExpectItemNameUpdated() {
        BestDealDto bestDealDto = new BestDealDto()
                .setItem("Apple Watch")
                .setWalmartPrice(new BigDecimal("300.50"))
                .setCostcoPrice(new BigDecimal("250.00"));

        BestDealDto createdDealDto = testRestTemplate.postForObject("/api/best-deals", bestDealDto, BestDealDto.class);
        createdDealDto.setItem("Samsung Watch");

        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/api/best-deals/" + createdDealDto.getId(), HttpMethod.PUT, new HttpEntity<>(createdDealDto), Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());

        BestDealDto updatedDealDto = testRestTemplate.getForObject("/api/best-deals/" + createdDealDto.getId(), BestDealDto.class);

        assertAll(
                () -> assertEquals(createdDealDto.getId(), updatedDealDto.getId()),
                () -> assertEquals(createdDealDto.getItem(), updatedDealDto.getItem()),
                () -> assertEquals(createdDealDto.getWalmartPrice(), updatedDealDto.getWalmartPrice()),
                () -> assertEquals(createdDealDto.getCostcoPrice(), updatedDealDto.getCostcoPrice())
        );
    }

    @Test
    void testFindAll_NoDeals_ExpectEmptyList() {
        ResponseEntity<BestDealDto[]> responseEntity = testRestTemplate.getForEntity("/api/best-deals", BestDealDto[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(0, responseEntity.getBody().length);
    }

    @Test
    void testFindAll_3Deals_Expect3() {
        BestDealDto bestDealDto = new BestDealDto()
                .setItem("Apple Watch")
                .setWalmartPrice(new BigDecimal("300.50"))
                .setCostcoPrice(new BigDecimal("250.00"));

        testRestTemplate.postForObject("/api/best-deals", bestDealDto, BestDealDto.class);
        testRestTemplate.postForObject("/api/best-deals", bestDealDto, BestDealDto.class);
        testRestTemplate.postForObject("/api/best-deals", bestDealDto, BestDealDto.class);

        ResponseEntity<BestDealDto[]> responseEntity = testRestTemplate.getForEntity("/api/best-deals", BestDealDto[].class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(3, responseEntity.getBody().length);
    }

    @Test
    void testDelete_Deal_ExpectDeleted() {
        BestDealDto bestDealDto = new BestDealDto()
                .setItem("Apple Watch")
                .setWalmartPrice(new BigDecimal("300.50"))
                .setCostcoPrice(new BigDecimal("250.00"));

        BestDealDto createdDealDto = testRestTemplate.postForObject("/api/best-deals", bestDealDto, BestDealDto.class);

        ResponseEntity<Void> responseEntity = testRestTemplate.exchange("/api/best-deals/" + createdDealDto.getId(), HttpMethod.DELETE, new HttpEntity<>(null), Void.class);

        assertEquals(HttpStatus.NO_CONTENT, responseEntity.getStatusCode());
        assertNull(responseEntity.getBody());

        ResponseEntity<BestDealDto> getResponseEntity = testRestTemplate.getForEntity("/api/best-deals/" + createdDealDto.getId(), BestDealDto.class);
        assertEquals(HttpStatus.NOT_FOUND, getResponseEntity.getStatusCode());
    }
}