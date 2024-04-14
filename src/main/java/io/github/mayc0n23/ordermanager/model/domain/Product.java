package io.github.mayc0n23.ordermanager.model.domain;

import io.github.mayc0n23.ordermanager.exception.DomainValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;

import static io.github.mayc0n23.ordermanager.utils.Constraints.MAX_ID_VALUE;
import static io.github.mayc0n23.ordermanager.utils.Constraints.MIN_ID_VALUE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NULL_ID_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NULL_VALUE_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_SIZE_ID_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_VALUE_MESSAGE;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Product {

    static final String PRODUCT_DESCRIPTION = "produto";

    @EqualsAndHashCode.Include
    private final Long id;

    private final BigDecimal price;

    public Product(Long id, BigDecimal price) {
        this.id = validateId(id);
        this.price = validatePrice(price);
    }

    private Long validateId(Long id) {
        if (id == null) {
            throw new DomainValidationException(String.format(INVALID_NULL_ID_MESSAGE, PRODUCT_DESCRIPTION));
        }
        if (id < MIN_ID_VALUE || id > MAX_ID_VALUE) {
            throw new DomainValidationException(
                    String.format(INVALID_SIZE_ID_MESSAGE, PRODUCT_DESCRIPTION, MIN_ID_VALUE, MAX_ID_VALUE));
        }
        return id;
    }

    private BigDecimal validatePrice(BigDecimal price) {
        if (price == null) {
            throw new DomainValidationException(INVALID_NULL_VALUE_MESSAGE);
        }
        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new DomainValidationException(INVALID_VALUE_MESSAGE);
        }
        return price;
    }

}
