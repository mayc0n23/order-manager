package io.github.mayc0n23.ordermanager.converter;

import io.github.mayc0n23.ordermanager.model.domain.LineObject;
import io.github.mayc0n23.ordermanager.utils.DateUtils;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static io.github.mayc0n23.ordermanager.utils.Pattern.DATE_WITHOUT_SEPARATION;

public class TxtLineToLineObjectConverter implements LineObjectConverter {

    private static final int USER_ID_START_INDEX = 0;
    private static final int USER_ID_END_INDEX = 10;
    private static final int USER_NAME_START_INDEX = 10;
    private static final int RIGHT_TO_LEFT_USER_NAME_END_INDEX = 40;
    private static final int RIGHT_TO_LEFT_ORDER_ID_START_INDEX = 40;
    private static final int RIGHT_TO_LEFT_ORDER_ID_END_INDEX = 30;
    private static final int RIGHT_TO_LEFT_PRODUCT_ID_START_INDEX = 30;
    private static final int RIGHT_TO_LEFT_PRODUCT_ID_END_INDEX = 20;
    private static final int RIGHT_TO_LEFT_PRODUCT_PRICE_START_INDEX = 20;
    private static final int RIGHT_TO_LEFT_PRODUCT_PRICE_END_INDEX = 8;
    private static final int RIGHT_TO_LEFT_ORDER_DATE_START_INDEX = 8;

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
        return Long.parseLong(line.substring(USER_ID_START_INDEX, USER_ID_END_INDEX));
    }

    @Override
    public String extractUserName(String line) {
        return line.substring(USER_NAME_START_INDEX, line.length() - RIGHT_TO_LEFT_USER_NAME_END_INDEX).trim();
    }

    @Override
    public Long extractOrderId(String line) {
        return Long.parseLong(line.substring(line.length() - RIGHT_TO_LEFT_ORDER_ID_START_INDEX,
                line.length() - RIGHT_TO_LEFT_ORDER_ID_END_INDEX));
    }

    @Override
    public Long extractProductId(String line) {
        return Long.parseLong(line.substring(line.length() - RIGHT_TO_LEFT_PRODUCT_ID_START_INDEX,
                line.length() - RIGHT_TO_LEFT_PRODUCT_ID_END_INDEX));
    }

    @Override
    public BigDecimal extractProductPrice(String line) {
        final String productPrice = line.substring(line.length() - RIGHT_TO_LEFT_PRODUCT_PRICE_START_INDEX,
                line.length() - RIGHT_TO_LEFT_PRODUCT_PRICE_END_INDEX).trim();
        return new BigDecimal(productPrice);
    }

    @Override
    public LocalDate extractOrderDate(String line) {
        final String orderDate = line.substring(line.length() - RIGHT_TO_LEFT_ORDER_DATE_START_INDEX);
        return DateUtils.parseDateFromPattern(orderDate, DATE_WITHOUT_SEPARATION);
    }

}
