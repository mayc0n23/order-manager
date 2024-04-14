package io.github.mayc0n23.ordermanager.model.domain;

import io.github.mayc0n23.ordermanager.exception.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static io.github.mayc0n23.ordermanager.model.domain.Order.ORDER_DESCRIPTION;
import static io.github.mayc0n23.ordermanager.utils.Constraints.MAX_ID_VALUE;
import static io.github.mayc0n23.ordermanager.utils.Constraints.MIN_ID_VALUE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_EMPTY_PRODUCT_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_FUTURE_DATE_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NULL_DATE_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NULL_ID_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NULL_USER_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_SIZE_ID_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class OrderTest {

    @Test
    void shouldCreateOrder() {
        final var user = new User(1L, "user");
        final var bike = new Product(1L, BigDecimal.valueOf(100));
        final var notebook = new Product(1L, BigDecimal.valueOf(1200));

        final var order = assertDoesNotThrow(() ->
                new Order(1L, LocalDate.of(2024, 1, 20), user,
                        List.of(bike, notebook)));

        assertEquals(BigDecimal.valueOf(1300), order.getTotal());
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new Order(null, null, null, null));

        assertEquals(String.format(INVALID_NULL_ID_MESSAGE, ORDER_DESCRIPTION), ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(longs = {-1L, 99999999990L})
    void shouldExceptionWhenIdIsOutOfRange(long id) {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new Order(id, null, null, null));

        assertEquals(String.format(INVALID_SIZE_ID_MESSAGE, ORDER_DESCRIPTION, MIN_ID_VALUE, MAX_ID_VALUE),
                ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsNull() {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new Order(1L, null, null, null));

        assertEquals(INVALID_NULL_DATE_MESSAGE, ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenDateIsAfterNow() {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new Order(1L, LocalDate.now().plusDays(1), null, null));

        assertEquals(INVALID_FUTURE_DATE_MESSAGE, ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenUserIsNull() {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new Order(1L, LocalDate.of(2024, 1, 20), null, null));

        assertEquals(String.format(INVALID_NULL_USER_MESSAGE), ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProductsAreEmpty() {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new Order(1L, LocalDate.of(2024, 1, 20),
                        new User(1L, "user"), Collections.emptyList()));

        assertEquals(INVALID_EMPTY_PRODUCT_MESSAGE, ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenProductsIsNull() {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new Order(1L, LocalDate.of(2024, 1, 20),
                        new User(1L, "user"), null));

        assertEquals(INVALID_EMPTY_PRODUCT_MESSAGE, ex.getMessage());
    }

}