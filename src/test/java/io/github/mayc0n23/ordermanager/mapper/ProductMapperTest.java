package io.github.mayc0n23.ordermanager.mapper;

import io.github.mayc0n23.ordermanager.model.domain.Product;
import io.github.mayc0n23.ordermanager.model.entity.ProductEntity;
import io.github.mayc0n23.ordermanager.model.response.ProductResponse;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ProductMapperTest {

    @Test
    void shouldConvertProductToProductEntity() {
        final Product product = new Product(1L, BigDecimal.TEN);

        final ProductEntity productEntity = ProductMapper.toEntity(product);

        assertEquals(product.getId(), productEntity.getId());
        assertEquals(product.getPrice(), productEntity.getPrice());
    }

    @Test
    void shouldConvertProductListToProductEntityList() {
        final Product bike = new Product(1L, BigDecimal.TEN);
        final Product notebook = new Product(2L, BigDecimal.ONE);

        final List<Product> products = List.of(bike, notebook);

        final List<ProductEntity> productEntities = ProductMapper.toEntityList(products);

        assertEquals(2, productEntities.size());
        assertEquals(bike.getId(), productEntities.get(0).getId());
        assertEquals(bike.getPrice(), productEntities.get(0).getPrice());
        assertEquals(notebook.getId(), productEntities.get(1).getId());
        assertEquals(notebook.getPrice(), productEntities.get(1).getPrice());
    }

    @Test
    void shouldConvertProductEntityToProduct() {
        final ProductEntity productEntity = new ProductEntity(1L, BigDecimal.TEN);

        final Product product = ProductMapper.toDomain(productEntity);

        assertEquals(productEntity.getId(), product.getId());
        assertEquals(productEntity.getPrice(), product.getPrice());
    }

    @Test
    void shouldConvertProductEntityListToProductList() {
        final ProductEntity bikeEntity = new ProductEntity(1L, BigDecimal.TEN);
        final ProductEntity notebookEntity = new ProductEntity(2L, BigDecimal.ONE);

        final List<ProductEntity> productEntities = List.of(bikeEntity, notebookEntity);

        final List<Product> products = ProductMapper.toDomainList(productEntities);

        assertEquals(2, products.size());
        assertEquals(bikeEntity.getId(), products.get(0).getId());
        assertEquals(bikeEntity.getPrice(), products.get(0).getPrice());
        assertEquals(notebookEntity.getId(), products.get(1).getId());
        assertEquals(notebookEntity.getPrice(), products.get(1).getPrice());
    }

    @Test
    void shouldConvertProductToProductResponse() {
        final Product product = new Product(1L, BigDecimal.TEN);

        final ProductResponse productResponse = ProductMapper.toResponse(product);

        assertEquals(product.getId(), productResponse.productId());
        assertEquals(product.getPrice(), productResponse.value());
    }

    @Test
    void shouldConvertProductListToProductResponseList() {
        final Product bike = new Product(1L, BigDecimal.TEN);
        final Product notebook = new Product(2L, BigDecimal.ONE);

        final List<Product> products = List.of(bike, notebook);

        final List<ProductResponse> productResponses = ProductMapper.toResponseList(products);

        assertEquals(2, productResponses.size());
        assertEquals(bike.getId(), productResponses.get(0).productId());
        assertEquals(bike.getPrice(), productResponses.get(0).value());
        assertEquals(notebook.getId(), productResponses.get(1).productId());
        assertEquals(notebook.getPrice(), productResponses.get(1).value());
    }

}