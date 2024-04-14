package io.github.mayc0n23.ordermanager.mapper;

import io.github.mayc0n23.ordermanager.model.domain.Order;
import io.github.mayc0n23.ordermanager.model.entity.OrderEntity;
import io.github.mayc0n23.ordermanager.model.response.OrderResponse;

import java.util.List;

public class OrderMapper {

    public static OrderEntity toEntity(Order order) {
        return new OrderEntity(
                order.getId(),
                order.getDate(),
                UserMapper.toEntity(order.getUser()),
                ProductMapper.toEntityList(order.getProducts()));
    }

    public static List<OrderEntity> toEntityList(List<Order> orders) {
        return orders.stream()
                .map(OrderMapper::toEntity)
                .toList();
    }

    public static Order toDomain(OrderEntity orderEntity) {
        return new Order(
                orderEntity.getId(),
                orderEntity.getDate(),
                UserMapper.toDomain(orderEntity.getUser()),
                ProductMapper.toDomainList(orderEntity.getProducts()));
    }

    public static List<Order> toDomainList(List<OrderEntity> orderEntities) {
        return orderEntities.stream()
                .map(OrderMapper::toDomain)
                .toList();
    }

    public static OrderResponse toResponse(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getTotal(),
                order.getDate(),
                ProductMapper.toResponseList(order.getProducts()));
    }

}