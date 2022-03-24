package ir.bootcamp.oneline.shop.service;

import ir.bootcamp.oneline.shop.model.Category;
import ir.bootcamp.oneline.shop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    List<Product> findAll();

    List<Product> findByCategory(Category category);

    Product find(Long id);

    void createProduct(String name, Category category, Long price, Integer quantity);

    void increaseQuantity(Long productId, Integer quantity);

    void update(Product product);
}
