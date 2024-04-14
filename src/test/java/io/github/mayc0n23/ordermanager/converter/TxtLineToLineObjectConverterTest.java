package io.github.mayc0n23.ordermanager.converter;

import io.github.mayc0n23.ordermanager.model.domain.LineObject;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class TxtLineToLineObjectConverterTest {

    @Test
    void shouldConvertTxtLineToLineObject() {
        final String line = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        final LineObjectConverter converter = new TxtLineToLineObjectConverter();

        final LineObject lineObject = converter.convert(line);

        assertEquals(70, lineObject.getUserId());
        assertEquals("Palmer Prosacco", lineObject.getUserName());
        assertEquals(753, lineObject.getOrderId());
        assertEquals(3, lineObject.getProductId());
        assertEquals(BigDecimal.valueOf(1836.74), lineObject.getProductPrice());
        assertEquals(LocalDate.of(2021, 3, 8), lineObject.getOrderDate());
    }

    @Test
    void shouldConvertTxtLineListToLineObjectList() {
        final String line = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        final List<String> lines = Collections.singletonList(line);
        final LineObjectConverter converter = new TxtLineToLineObjectConverter();

        final List<LineObject> lineObjects = converter.convert(lines);

        assertFalse(lineObjects.isEmpty());
        assertEquals(1, lineObjects.size());
        assertEquals(70, lineObjects.get(0).getUserId());
        assertEquals("Palmer Prosacco", lineObjects.get(0).getUserName());
        assertEquals(753, lineObjects.get(0).getOrderId());
        assertEquals(3, lineObjects.get(0).getProductId());
        assertEquals(BigDecimal.valueOf(1836.74), lineObjects.get(0).getProductPrice());
        assertEquals(LocalDate.of(2021, 3, 8), lineObjects.get(0).getOrderDate());
    }

    @Test
    void shouldExtractUserId() {
        final String line = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        final LineObjectConverter converter = new TxtLineToLineObjectConverter();

        final Long userId = converter.extractUserId(line);

        assertEquals(70, userId);
    }

    @Test
    void shouldExtractUserName() {
        final String line = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        final LineObjectConverter converter = new TxtLineToLineObjectConverter();

        final String userName = converter.extractUserName(line);

        assertEquals("Palmer Prosacco", userName);
    }

    @Test
    void shouldExtractOrderId() {
        final String line = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        final LineObjectConverter converter = new TxtLineToLineObjectConverter();

        final Long orderId = converter.extractOrderId(line);

        assertEquals(753, orderId);
    }

    @Test
    void shouldExtractProductId() {
        final String line = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        final LineObjectConverter converter = new TxtLineToLineObjectConverter();

        final Long productId = converter.extractProductId(line);

        assertEquals(3, productId);
    }

    @Test
    void shouldExtractProductPrice() {
        final String line = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        final LineObjectConverter converter = new TxtLineToLineObjectConverter();

        final BigDecimal productPrice = converter.extractProductPrice(line);

        assertEquals(BigDecimal.valueOf(1836.74), productPrice);
    }

    @Test
    void shouldExtractOrderDate() {
        final String line = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        final LineObjectConverter converter = new TxtLineToLineObjectConverter();

        final LocalDate orderDate = converter.extractOrderDate(line);

        assertEquals(LocalDate.of(2021, 3, 8), orderDate);
    }

}