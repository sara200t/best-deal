package com.jimandpengsadventure.bestdeal.core.repository;

import com.jimandpengsadventure.bestdeal.core.entity.Deal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface DealRepository extends JpaRepository<Deal, UUID> {
    List<Deal> findDealsByItemIgnoreCase(String item);
}
