package io.github.mayc0n23.ordermanager.model.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record OrderDataResponse(@JsonProperty("user_id") Long userId, String name, List<OrderResponse> orders) { }
