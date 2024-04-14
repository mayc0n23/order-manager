package io.github.mayc0n23.ordermanager.mapper;

import io.github.mayc0n23.ordermanager.model.domain.Order;
import io.github.mayc0n23.ordermanager.model.domain.Product;
import io.github.mayc0n23.ordermanager.model.domain.User;
import io.github.mayc0n23.ordermanager.model.entity.OrderEntity;
import io.github.mayc0n23.ordermanager.model.entity.ProductEntity;
import io.github.mayc0n23.ordermanager.model.entity.UserEntity;
import io.github.mayc0n23.ordermanager.model.response.OrderResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class OrderMapperTest {

    @Test
    void shouldConvertOrderToOrderEntity() {
        final User user = new User(1L, "John Doe");
        final Product product = new Product(1L, BigDecimal.TEN);
        final Order order = new Order(1L, LocalDate.of(2023, 10, 10), user,
                Collections.singletonList(product));

        final OrderEntity orderEntity = OrderMapper.toEntity(order);

        assertEquals(order.getId(), orderEntity.getId());
        assertEquals(order.getDate(), orderEntity.getDate());
        assertEquals(order.getUser().getId(), orderEntity.getId());
        assertEquals(order.getUser().getName(), orderEntity.getUser().getName());
        assertEquals(order.getProducts().get(0).getId(), orderEntity.getProducts().get(0).getId());
        assertEquals(order.getProducts().get(0).getPrice(), orderEntity.getProducts().get(0).getPrice());
    }

    @Test
    void shouldConvertOrderListToOrderEntityList() {
        final User user = new User(1L, "John Doe");
        final Product product = new Product(1L, BigDecimal.TEN);
        final Order order = new Order(1L, LocalDate.of(2023, 10, 10), user,
                Collections.singletonList(product));

        final List<OrderEntity> orderEntityList = OrderMapper.toEntityList(Collections.singletonList(order));

        assertEquals(order.getId(), orderEntityList.get(0).getId());
        assertEquals(order.getDate(), orderEntityList.get(0).getDate());
        assertEquals(order.getUser().getId(), orderEntityList.get(0).getId());
        assertEquals(order.getUser().getName(), orderEntityList.get(0).getUser().getName());
        assertEquals(order.getProducts().get(0).getId(), orderEntityList.get(0).getProducts().get(0).getId());
        assertEquals(order.getProducts().get(0).getPrice(), orderEntityList.get(0).getProducts().get(0).getPrice());
    }

    @Test
    void shouldConvertOrderEntityToOrder() {
        final UserEntity userEntity = new UserEntity(1L, "John Doe");
        final ProductEntity productEntity = new ProductEntity(1L, BigDecimal.TEN);
        final OrderEntity orderEntity = new OrderEntity(1L, LocalDate.of(2023, 10, 10), userEntity,
                Collections.singletonList(productEntity));

        final Order order = OrderMapper.toDomain(orderEntity);

        assertEquals(orderEntity.getId(), order.getId());
        assertEquals(orderEntity.getDate(), order.getDate());
        assertEquals(orderEntity.getUser().getId(), order.getUser().getId());
        assertEquals(orderEntity.getUser().getName(), order.getUser().getName());
        assertEquals(orderEntity.getProducts().get(0).getId(), order.getProducts().get(0).getId());
        assertEquals(orderEntity.getProducts().get(0).getPrice(), order.getProducts().get(0).getPrice());
    }

    @Test
    void shouldConvertOrderEntityListToOrderList() {
        final UserEntity userEntity = new UserEntity(1L, "John Doe");
        final ProductEntity productEntity = new ProductEntity(1L, BigDecimal.TEN);
        final OrderEntity orderEntity = new OrderEntity(1L, LocalDate.of(2023, 10, 10), userEntity,
                Collections.singletonList(productEntity));

        final List<Order> orderList = OrderMapper.toDomainList(Collections.singletonList(orderEntity));

        assertEquals(orderEntity.getId(), orderList.get(0).getId());
        assertEquals(orderEntity.getDate(), orderList.get(0).getDate());
        assertEquals(orderEntity.getUser().getId(), orderList.get(0).getUser().getId());
        assertEquals(orderEntity.getUser().getName(), orderList.get(0).getUser().getName());
        assertEquals(orderEntity.getProducts().get(0).getId(), orderList.get(0).getProducts().get(0).getId());
        assertEquals(orderEntity.getProducts().get(0).getPrice(), orderList.get(0).getProducts().get(0).getPrice());
    }

    @Test
    void shouldConvertOrderToOrderResponse() {
        final User user = new User(1L, "John Doe");
        final Product product = new Product(1L, BigDecimal.TEN);
        final Order order = new Order(1L, LocalDate.of(2023, 10, 10), user,
                Collections.singletonList(product));

        final OrderResponse orderResponse = OrderMapper.toResponse(order);

        assertEquals(order.getId(), orderResponse.orderId());
        assertEquals(order.getTotal(), orderResponse.total());
        assertEquals(order.getDate(), orderResponse.date());
        assertEquals(order.getProducts().get(0).getId(), orderResponse.products().get(0).productId());
        assertEquals(order.getProducts().get(0).getPrice(), orderResponse.products().get(0).value());
    }

}