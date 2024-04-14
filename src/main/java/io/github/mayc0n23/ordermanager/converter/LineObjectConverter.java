package io.github.mayc0n23.ordermanager.converter;

import io.github.mayc0n23.ordermanager.model.domain.LineObject;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface LineObjectConverter {

    LineObject convert(String source);

    List<LineObject> convert(List<String> source);

    Long extractUserId(String line);

    String extractUserName(String line);

    Long extractOrderId(String line);

    LocalDate extractOrderDate(String line);

    Long extractProductId(String line);

    BigDecimal extractProductPrice(String line);

}