package io.github.mayc0n23.ordermanager.repository;

import io.github.mayc0n23.ordermanager.model.entity.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> { }