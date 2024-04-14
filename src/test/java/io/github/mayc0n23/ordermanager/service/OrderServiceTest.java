package io.github.mayc0n23.ordermanager.service;

import io.github.mayc0n23.ordermanager.exception.EntityNotFoundException;
import io.github.mayc0n23.ordermanager.mapper.OrderMapper;
import io.github.mayc0n23.ordermanager.model.domain.Order;
import io.github.mayc0n23.ordermanager.model.domain.Product;
import io.github.mayc0n23.ordermanager.model.domain.User;
import io.github.mayc0n23.ordermanager.repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldSaveOrders() {
        final User user = new User(1L, "John Doe");
        final Product product = new Product(1L, BigDecimal.TEN);
        final List<Order> orders = List.of(new Order(1L, LocalDate.of(2024, 1, 20),
                user, Collections.singletonList(product)));

        orderService.saveOrders(orders);

        verify(orderRepository, times(1)).saveAll(OrderMapper.toEntityList(orders));
    }

    @Test
    void shouldReturnOrderById() {
        final User user = new User(1L, "John Doe");
        final Product product = new Product(1L, BigDecimal.TEN);
        final Order order = new Order(1L, LocalDate.of(2024, 1, 20), user,
                Collections.singletonList(product));

        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(OrderMapper.toEntity(order)));

        final var result = orderService.getOrderById(1L);

        assertNotNull(result);
        verify(orderRepository, times(1)).findById(anyLong());
    }

    @Test
    void shouldThrowEntityNotFoundExceptionWhenOrderNotFound() {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderService.getOrderById(1L));
    }

    @Test
    void shouldReturnAllOrders() {
        final User user = new User(1L, "John Doe");
        final Product product = new Product(1L, BigDecimal.TEN);
        final Order order = new Order(1L, LocalDate.of(2024, 1, 20), user,
                Collections.singletonList(product));

        when(orderRepository.findAll()).thenReturn(Collections.singletonList(OrderMapper.toEntity(order)));

        final var result = orderService.getOrders(null, null);

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        verify(orderRepository, times(1)).findAll();
        verify(orderRepository, never()).findByDateBetween(any(LocalDate.class), any(LocalDate.class));
    }

    @Test
    void shouldReturnOrdersByDateRange() {
        final User user = new User(1L, "John Doe");
        final Product product = new Product(1L, BigDecimal.TEN);
        final Order order = new Order(1L, LocalDate.of(2024, 1, 20), user,
                Collections.singletonList(product));

        when(orderRepository.findByDateBetween(any(LocalDate.class), any(LocalDate.class)))
                .thenReturn(Collections.singletonList(OrderMapper.toEntity(order)));

        final var result = orderService.getOrders("2023-02-03", "2023-03-01");

        assertNotNull(result);
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());

        verify(orderRepository, times(1)).findByDateBetween(any(LocalDate.class), any(LocalDate.class));
        verify(orderRepository, never()).findAll();
    }

}