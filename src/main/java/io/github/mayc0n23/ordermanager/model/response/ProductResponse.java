package io.github.mayc0n23.ordermanager.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public record ProductResponse(@JsonProperty("product_id") Long productId, BigDecimal value) { }
