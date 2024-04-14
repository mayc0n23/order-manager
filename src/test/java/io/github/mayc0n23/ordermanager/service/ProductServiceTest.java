package io.github.mayc0n23.ordermanager.service;

import io.github.mayc0n23.ordermanager.mapper.ProductMapper;
import io.github.mayc0n23.ordermanager.model.domain.Product;
import io.github.mayc0n23.ordermanager.repository.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.List;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void shouldSaveProducts() {
        final List<Product> products = List.of(new Product(1L, BigDecimal.TEN));

        productService.saveProducts(products);

        verify(productRepository, times(1)).saveAll(ProductMapper.toEntityList(products));
    }

}