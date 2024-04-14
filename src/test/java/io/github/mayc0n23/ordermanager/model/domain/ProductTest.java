package io.github.mayc0n23.ordermanager.model.domain;

import io.github.mayc0n23.ordermanager.exception.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;

import static io.github.mayc0n23.ordermanager.model.domain.Product.PRODUCT_DESCRIPTION;
import static io.github.mayc0n23.ordermanager.utils.Constraints.MAX_ID_VALUE;
import static io.github.mayc0n23.ordermanager.utils.Constraints.MIN_ID_VALUE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NULL_ID_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NULL_VALUE_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_SIZE_ID_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_VALUE_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void shouldCreateProduct() {
        assertDoesNotThrow(() -> new Product(1L, BigDecimal.ONE));
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new Product(null, BigDecimal.ONE));

        assertEquals(String.format(INVALID_NULL_ID_MESSAGE, PRODUCT_DESCRIPTION), ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(longs = {-1L, 99999999990L})
    void shouldThrowExceptionWhenIdIsOutOfRange(long id) {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new Product(id, BigDecimal.ONE));

        assertEquals(String.format(INVALID_SIZE_ID_MESSAGE, PRODUCT_DESCRIPTION, MIN_ID_VALUE, MAX_ID_VALUE),
                ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenValueIsNull() {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new Product(1L, null));

        assertEquals(INVALID_NULL_VALUE_MESSAGE, ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenValueIsLessThanZero() {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new Product(1L, BigDecimal.valueOf(-1)));

        assertEquals(INVALID_VALUE_MESSAGE, ex.getMessage());
    }

}