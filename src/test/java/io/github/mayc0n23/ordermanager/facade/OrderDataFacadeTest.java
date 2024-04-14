package io.github.mayc0n23.ordermanager.facade;

import io.github.mayc0n23.ordermanager.model.domain.Order;
import io.github.mayc0n23.ordermanager.model.domain.Product;
import io.github.mayc0n23.ordermanager.model.domain.User;
import io.github.mayc0n23.ordermanager.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderDataFacadeTest {

    @Mock
    private OrderService orderService;

    @InjectMocks
    private OrderDataFacade orderDataFacade;

    @Test
    void shouldFindOrderByIdReturnOrderDataResponse() {
        final var user = new User(1L, "John Doe");
        final var product = new Product(1L, BigDecimal.TEN);
        final Order order = new Order(1L,
                LocalDate.of(2023, 3, 8), user, Collections.singletonList(product));

        when(orderService.getOrderById(anyLong())).thenReturn(order);

        final var result = orderDataFacade.findOrderById(1L);

        assertEquals(user.getId(), result.userId());
        assertEquals(user.getName(), result.name());
        assertEquals(1, result.orders().size());
        assertEquals(order.getId(), result.orders().get(0).orderId());
        assertEquals(order.getDate(), result.orders().get(0).date());
        assertEquals(order.getProducts().size(), result.orders().get(0).products().size());
        assertEquals(product.getId(), result.orders().get(0).products().get(0).productId());
        assertEquals(product.getPrice(), result.orders().get(0).products().get(0).value());

        verify(orderService, times(1)).getOrderById(1L);
    }

    @Test
    void shouldFindOrdersReturnOrderDataResponseList() {
        final var user = new User(1L, "John Doe");
        final var product = new Product(1L, BigDecimal.TEN);
        final Order order = new Order(1L,
                LocalDate.of(2023, 3, 8), user, Collections.singletonList(product));

        when(orderService.getOrders(anyString(), anyString())).thenReturn(Collections.singletonList(order));

        final var result = orderDataFacade.findOrders(anyString(), anyString());

        assertEquals(1, result.size());
        assertEquals(user.getId(), result.get(0).userId());
        assertEquals(user.getName(), result.get(0).name());
        assertEquals(1, result.get(0).orders().size());
        assertEquals(order.getId(), result.get(0).orders().get(0).orderId());
        assertEquals(order.getDate(), result.get(0).orders().get(0).date());
        assertEquals(order.getProducts().size(), result.get(0).orders().get(0).products().size());
        assertEquals(product.getId(), result.get(0).orders().get(0).products().get(0).productId());
        assertEquals(product.getPrice(), result.get(0).orders().get(0).products().get(0).value());

        verify(orderService, times(1)).getOrders(anyString(), anyString());
    }

}