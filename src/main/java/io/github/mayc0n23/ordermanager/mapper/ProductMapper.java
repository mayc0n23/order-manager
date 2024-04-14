package io.github.mayc0n23.ordermanager.mapper;

import io.github.mayc0n23.ordermanager.model.domain.Product;
import io.github.mayc0n23.ordermanager.model.entity.ProductEntity;
import io.github.mayc0n23.ordermanager.model.response.ProductResponse;

import java.util.List;

public class ProductMapper {

    public static ProductEntity toEntity(Product product) {
        return new ProductEntity(product.getId(), product.getPrice());
    }

    public static List<ProductEntity> toEntityList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toEntity)
                .toList();
    }

    public static Product toDomain(ProductEntity productEntity) {
        return new Product(productEntity.getId(), productEntity.getPrice());
    }

    public static List<Product> toDomainList(List<ProductEntity> productEntities) {
        return productEntities.stream()
                .map(ProductMapper::toDomain)
                .toList();
    }

    public static ProductResponse toResponse(Product product) {
        return new ProductResponse(product.getId(), product.getPrice());
    }

    public static List<ProductResponse> toResponseList(List<Product> products) {
        return products.stream()
                .map(ProductMapper::toResponse)
                .toList();
    }

}