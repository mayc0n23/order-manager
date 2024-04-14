package io.github.mayc0n23.ordermanager.repository;

import io.github.mayc0n23.ordermanager.model.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<OrderEntity, Long> {

    List<OrderEntity> findByDateBetween(LocalDate start, LocalDate end);

}