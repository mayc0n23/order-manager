package io.github.mayc0n23.ordermanager.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record OrderResponse(
        @JsonProperty("order_id")
        Long orderId,
        BigDecimal total,
        LocalDate date,
        List<ProductResponse> products) { }
