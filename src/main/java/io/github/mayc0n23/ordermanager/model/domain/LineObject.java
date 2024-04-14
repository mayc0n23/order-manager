package io.github.mayc0n23.ordermanager.model.domain;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@RequiredArgsConstructor
@Getter
public class LineObject {

    private final Long userId;

    private final String userName;

    private final Long orderId;

    private final Long productId;

    private final BigDecimal productPrice;

    private final LocalDate orderDate;

}