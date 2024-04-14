package io.github.mayc0n23.ordermanager.service;

import io.github.mayc0n23.ordermanager.exception.EntityNotFoundException;
import io.github.mayc0n23.ordermanager.mapper.OrderMapper;
import io.github.mayc0n23.ordermanager.model.domain.Order;
import io.github.mayc0n23.ordermanager.repository.OrderRepository;
import io.github.mayc0n23.ordermanager.utils.DateUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static io.github.mayc0n23.ordermanager.utils.ErrorMessage.ORDER_NOT_FOUND_MESSAGE;

@Service
public class OrderService {

    private final OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Transactional
    public void saveOrders(List<Order> orders) {
        final var orderEntities = OrderMapper.toEntityList(orders);
        orderRepository.saveAll(orderEntities);
    }

    public Order getOrderById(Long id) {
        final var orderEntity = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format(ORDER_NOT_FOUND_MESSAGE, id)));
        return OrderMapper.toDomain(orderEntity);
    }

    public List<Order> getOrders(String start, String end) {
        if (hasDates(start, end)) {
            return getOrdersByDateRange(start, end);
        }
        final var orderEntities = orderRepository.findAll();
        return OrderMapper.toDomainList(orderEntities);
    }

    private List<Order> getOrdersByDateRange(String start, String end) {
        final String pattern = "yyyy-MM-dd";
        final var startDate = DateUtils.parseDateFromPattern(start, pattern);
        final var endDate = DateUtils.parseDateFromPattern(end, pattern);
        final var orderEntities = orderRepository.findByDateBetween(startDate, endDate);
        return OrderMapper.toDomainList(orderEntities);
    }

    private boolean hasDates(String start, String end) {
        return start != null && end != null;
    }

}