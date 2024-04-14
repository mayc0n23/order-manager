package io.github.mayc0n23.ordermanager.model.domain;

import io.github.mayc0n23.ordermanager.exception.DomainValidationException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static io.github.mayc0n23.ordermanager.model.domain.User.USER_DESCRIPTION;
import static io.github.mayc0n23.ordermanager.utils.Constraints.MAX_ID_VALUE;
import static io.github.mayc0n23.ordermanager.utils.Constraints.MIN_ID_VALUE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_EMPTY_NAME_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NAME_LENGTH_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NULL_ID_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_SIZE_ID_MESSAGE;
import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void shouldCreateUser() {
        assertDoesNotThrow(() -> new User(1L, "John Doe"));
    }

    @Test
    void shouldThrowExceptionWhenIdIsNull() {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new User(null, "John Doe"));

        assertEquals(String.format(INVALID_NULL_ID_MESSAGE, USER_DESCRIPTION), ex.getMessage());
    }

    @ParameterizedTest
    @ValueSource(longs = {-1L, 99999999990L})
    void shouldThrowExceptionWhenIdIsOutOfRange(long id) {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new User(id, "John Doe"));

        assertEquals(String.format(INVALID_SIZE_ID_MESSAGE, USER_DESCRIPTION, MIN_ID_VALUE, MAX_ID_VALUE),
                ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsNull() {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new User(1L, null));

        assertEquals(INVALID_EMPTY_NAME_MESSAGE, ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsBlank() {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new User(1L, ""));

        assertEquals(INVALID_EMPTY_NAME_MESSAGE, ex.getMessage());
    }

    @Test
    void shouldThrowExceptionWhenNameIsTooLong() {
        DomainValidationException ex = assertThrows(DomainValidationException.class,
                () -> new User(1L, "01234567890123456789012345678901234567890123456789"));

        assertEquals(INVALID_NAME_LENGTH_MESSAGE, ex.getMessage());
    }

}