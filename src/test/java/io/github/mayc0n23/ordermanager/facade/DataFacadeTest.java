package io.github.mayc0n23.ordermanager.facade;

import io.github.mayc0n23.ordermanager.model.domain.LineObject;
import io.github.mayc0n23.ordermanager.model.domain.Order;
import io.github.mayc0n23.ordermanager.model.domain.Product;
import io.github.mayc0n23.ordermanager.model.domain.User;
import io.github.mayc0n23.ordermanager.service.OrderService;
import io.github.mayc0n23.ordermanager.service.ProductService;
import io.github.mayc0n23.ordermanager.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class DataFacadeTest {

    @Mock
    private ProductService productService;

    @Mock
    private OrderService orderService;

    @Mock
    private UserService userService;

    @InjectMocks
    private DataFacade dataFacade;

    @Test
    void shouldUpload() {
        final String content = "0000000070                              Palmer Prosacco00000007530000000003     1836.7420210308";
        final InputStream inputStream = new ByteArrayInputStream(content.getBytes());

        dataFacade.upload(inputStream);

        verify(productService, times(1)).saveProducts(anyList());
        verify(userService, times(1)).saveUsers(anyList());
        verify(orderService, times(1)).saveOrders(anyList());
    }

    @Test
    void shouldExtractProducts() {
        final LineObject lineObject = new LineObject(
                70L,
                "Palmer Prosacco",
                753L,
                3L,
                BigDecimal.valueOf(1836.74),
                LocalDate.of(2021, 3, 8));

        final Set<Product> products = dataFacade.extractProducts(Collections.singletonList(lineObject));

        assertEquals(1L, products.size());
        assertEquals(3L, products.iterator().next().getId());
        assertEquals(BigDecimal.valueOf(1836.74), products.iterator().next().getPrice());
    }

    @Test
    void shouldExtractUsers() {
        final LineObject lineObject = new LineObject(
                70L,
                "Palmer Prosacco",
                753L,
                3L,
                BigDecimal.valueOf(1836.74),
                LocalDate.of(2021, 3, 8));

        final Set<User> users = dataFacade.extractUsers(Collections.singletonList(lineObject));

        assertEquals(1L, users.size());
        assertEquals(70L, users.iterator().next().getId());
        assertEquals("Palmer Prosacco", users.iterator().next().getName());
    }

    @Test
    void shouldExtractOrders() {
        final LineObject lineObject = new LineObject(
                70L,
                "Palmer Prosacco",
                753L,
                3L,
                BigDecimal.valueOf(1836.74),
                LocalDate.of(2021, 3, 8));

        final Set<Order> orders = dataFacade.extractOrders(Collections.singletonList(lineObject));

        assertEquals(1L, orders.size());
        assertEquals(753L, orders.iterator().next().getId());
        assertEquals(LocalDate.of(2021, 3, 8), orders.iterator().next().getDate());
        assertEquals(70L, orders.iterator().next().getUser().getId());
        assertEquals("Palmer Prosacco", orders.iterator().next().getUser().getName());
        assertEquals(1L, orders.iterator().next().getProducts().size());
        assertEquals(3L, orders.iterator().next().getProducts().iterator().next().getId());
        assertEquals(BigDecimal.valueOf(1836.74), orders.iterator().next().getProducts().iterator().next().getPrice());
    }

}