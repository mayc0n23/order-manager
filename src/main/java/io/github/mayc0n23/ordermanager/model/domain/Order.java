package io.github.mayc0n23.ordermanager.model.domain;

import io.github.mayc0n23.ordermanager.exception.DomainValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static io.github.mayc0n23.ordermanager.utils.Constraints.MAX_ID_VALUE;
import static io.github.mayc0n23.ordermanager.utils.Constraints.MIN_ID_VALUE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_EMPTY_PRODUCT_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_FUTURE_DATE_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NULL_DATE_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NULL_ID_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_NULL_USER_MESSAGE;
import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.INVALID_SIZE_ID_MESSAGE;

@Getter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Order {

    static final String ORDER_DESCRIPTION = "pedido";

    @EqualsAndHashCode.Include
    private final Long id;

    private final LocalDate date;

    private final User user;

    private final List<Product> products;

    private final BigDecimal total;

    public Order(Long id, LocalDate date, User user, List<Product> products) {
        this.id = validateId(id);
        this.date = validateDate(date);
        this.user = validateUser(user);
        this.products = validateProducts(products);
        this.total = calculateTotal();
    }

    private Long validateId(Long id) {
        if (id == null) {
            throw new DomainValidationException(String.format(INVALID_NULL_ID_MESSAGE, ORDER_DESCRIPTION));
        }
        if (id < MIN_ID_VALUE || id > MAX_ID_VALUE) {
            throw new DomainValidationException(
                    String.format(INVALID_SIZE_ID_MESSAGE, ORDER_DESCRIPTION, MIN_ID_VALUE, MAX_ID_VALUE));
        }
        return id;
    }

    private LocalDate validateDate(LocalDate date) {
        if (date == null) {
            throw new DomainValidationException(INVALID_NULL_DATE_MESSAGE);
        }

        final LocalDate now = LocalDate.now();
        if (date.isAfter(now)) {
            throw new DomainValidationException(INVALID_FUTURE_DATE_MESSAGE);
        }

        return date;
    }

    private BigDecimal calculateTotal() {
        return products.stream().map(Product::getPrice).reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private User validateUser(User user) {
        if (user == null) {
            throw new DomainValidationException(INVALID_NULL_USER_MESSAGE);
        }
        return user;
    }

    private List<Product> validateProducts(List<Product> products) {
        if (products == null || products.isEmpty()) {
            throw new DomainValidationException(INVALID_EMPTY_PRODUCT_MESSAGE);
        }
        return products;
    }

}