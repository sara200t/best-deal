package com.jimandpengsadventure.bestdeal.core;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "DEAL")
@Accessors(chain = true)
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Deal {
    @Id
    @Column(name = "DEAL_ID")
    @Type(type = "uuid-char")
    @Setter(AccessLevel.NONE)
    private UUID id;

    @Column(name = "ITEM")
    private String item;

    @Column(name = "WALMART_PRICE")
    private BigDecimal walmartPrice;

    @Column(name = "COSTCO_PRICE")
    private BigDecimal costcoPrice;

    Deal() {
    }

    public static Deal newInstance() {
        Deal deal = new Deal();
        deal.id = UUID.randomUUID();
        return deal;
    }
}
