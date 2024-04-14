package io.github.mayc0n23.ordermanager.service;

import io.github.mayc0n23.ordermanager.mapper.ProductMapper;
import io.github.mayc0n23.ordermanager.model.domain.Product;
import io.github.mayc0n23.ordermanager.repository.ProductRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transactional
    public void saveProducts(List<Product> products) {
        final var productEntities = ProductMapper.toEntityList(products);
        productRepository.saveAll(productEntities);
    }

}