package com.dev2ever.api.rest;

import java.math.BigDecimal;

public record JsonBuy(BigDecimal amount, BigDecimal price, String description, Long idBook) {

}
