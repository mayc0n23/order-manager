package io.github.mayc0n23.ordermanager.converter;

import io.github.mayc0n23.ordermanager.model.domain.LineObject;
import io.github.mayc0n23.ordermanager.utils.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class TxtLineToLineObjectConverter implements LineObjectConverter {

    @Override
    public LineObject convert(String source) {
        final Long userId = extractUserId(source);
        final String userName = extractUserName(source);
        final Long orderId = extractOrderId(source);
        final Long productId = extractProductId(source);
        final BigDecimal productPrice = extractProductPrice(source);
        final LocalDate orderDate = extractOrderDate(source);
        return new LineObject(userId, userName, orderId, productId, productPrice, orderDate);
    }

    @Override
    public List<LineObject> convert(List<String> source) {
        return source.stream()
                .map(this::convert)
                .toList();
    }

    @Override
    public Long extractUserId(String line) {
        return Long.parseLong(line.substring(0, 10));
    }

    @Override
    public String extractUserName(String line) {
        return line.substring(10, line.length() - 40).trim();
    }

    @Override
    public Long extractOrderId(String line) {
        return Long.parseLong(line.substring(line.length() - 40, line.length() - 30));
    }

    @Override
    public Long extractProductId(String line) {
        return Long.parseLong(line.substring(line.length() - 30, line.length() - 20));
    }

    @Override
    public BigDecimal extractProductPrice(String line) {
        final String productPrice = line.substring(line.length() - 20, line.length() - 8).trim();
        return new BigDecimal(productPrice);
    }

    @Override
    public LocalDate extractOrderDate(String line) {
        final String orderDate = line.substring(line.length() - 8);
        return DateUtils.parseDateFromPattern(orderDate, "yyyyMMdd");
    }

}
