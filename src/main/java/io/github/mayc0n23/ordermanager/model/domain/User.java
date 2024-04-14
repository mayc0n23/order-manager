package io.github.mayc0n23.ordermanager.model.domain;

import io.github.mayc0n23.ordermanager.exception.DomainValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import static io.github.mayc0n23.ordermanager.utils.Constraints.MAX_ID_VALUE;
import static io.github.mayc0n23.ordermanager.utils.Constraints.MAX_NAME_LENGTH;
import static io.github.mayc0n23.ordermanager.utils.Constraints.MIN_ID_VALUE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_EMPTY_NAME_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NULL_ID_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_SIZE_ID_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NAME_LENGTH_MESSAGE;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class User {

    static final String USER_DESCRIPTION = "usuário";

    @EqualsAndHashCode.Include
    private final Long id;

    private final String name;

    public User(Long id, String name) {
        this.id = validateId(id);
        this.name = validateName(name);
    }

    private Long validateId(Long id) {
        if (id == null) {
            throw new DomainValidationException(String.format(INVALID_NULL_ID_MESSAGE, USER_DESCRIPTION));
        }
        if (id < MIN_ID_VALUE || id > MAX_ID_VALUE) {
            throw new DomainValidationException(
                    String.format(INVALID_SIZE_ID_MESSAGE, USER_DESCRIPTION, MIN_ID_VALUE, MAX_ID_VALUE));
        }
        return id;
    }

    private String validateName(String name) {
        if (name == null || name.isBlank()) {
            throw new DomainValidationException(INVALID_EMPTY_NAME_MESSAGE);
        }
        if (name.length() > MAX_NAME_LENGTH) {
            throw new DomainValidationException(INVALID_NAME_LENGTH_MESSAGE);
        }
        return name;
    }

}