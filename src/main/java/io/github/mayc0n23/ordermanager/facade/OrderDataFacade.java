package io.github.mayc0n23.ordermanager.facade;

import io.github.mayc0n23.ordermanager.mapper.OrderMapper;
import io.github.mayc0n23.ordermanager.model.domain.Order;
import io.github.mayc0n23.ordermanager.model.domain.User;
import io.github.mayc0n23.ordermanager.model.response.OrderDataResponse;
import io.github.mayc0n23.ordermanager.service.OrderService;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
public class OrderDataFacade {

    public OrderDataFacade(OrderService orderService) {
        this.orderService = orderService;
    }

    private final OrderService orderService;

    public OrderDataResponse findOrderById(Long id) {
        final var order = orderService.getOrderById(id);
        final var user = order.getUser();
        final var orderResponse = OrderMapper.toResponse(order);
        return new OrderDataResponse(user.getId(), user.getName(), Collections.singletonList(orderResponse));
    }

    public List<OrderDataResponse> findOrders(String start, String end) {
        final var response = new ArrayList<OrderDataResponse>();

        final var orders = orderService.getOrders(start, end);

        final Map<User, List<Order>> userOrdersMap = orders.stream()
                .collect(Collectors.groupingBy(Order::getUser));

        for (Map.Entry<User, List<Order>> entry : userOrdersMap.entrySet()) {
            final var user = entry.getKey();
            final var userOrders = entry.getValue();
            final var orderResponses = userOrders.stream()
                    .map(OrderMapper::toResponse)
                    .toList();
            final var orderData = new OrderDataResponse(user.getId(), user.getName(), orderResponses);
            response.add(orderData);
        }

        return response;
    }

}