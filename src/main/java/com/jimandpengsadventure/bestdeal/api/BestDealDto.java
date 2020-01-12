package com.jimandpengsadventure.bestdeal.api;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.UUID;
@Data
@Accessors(chain = true)
public class BestDealDto {
    private UUID id;
    private String item;
    private BigDecimal walmartPrice;
    private BigDecimal costcoPrice;
}