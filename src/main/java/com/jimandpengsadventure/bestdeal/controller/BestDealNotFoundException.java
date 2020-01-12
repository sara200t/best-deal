package com.jimandpengsadventure.bestdeal.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.UUID;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class BestDealNotFoundException extends RuntimeException {
    public BestDealNotFoundException(UUID id) {
        super(String.format("Could not find best deal with id %s", id));
    }
}
