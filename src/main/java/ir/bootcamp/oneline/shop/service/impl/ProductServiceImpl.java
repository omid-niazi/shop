package ir.bootcamp.oneline.shop.service.impl;

import ir.bootcamp.oneline.shop.common.Transaction;
import ir.bootcamp.oneline.shop.exceptions.ProductNotFoundException;
import ir.bootcamp.oneline.shop.model.Category;
import ir.bootcamp.oneline.shop.model.Product;
import ir.bootcamp.oneline.shop.repository.ProductRepository;
import ir.bootcamp.oneline.shop.service.ProductService;

import java.util.List;

public class ProductServiceImpl implements ProductService {
    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Transaction
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public List<Product> findByCategory(Category category) {
        return productRepository.findByCategory(category);
    }

    @Override
    @Transaction
    public void createProduct(String name, Category category, Long price, Integer quantity) {
        Product product = new Product(name, category, price, quantity);
        productRepository.save(product);
    }

    @Override
    public Product find(Long id) {
        return productRepository.find(id)
                .orElseThrow(ProductNotFoundException::new);
    }

    @Override
    @Transaction
    public void increaseQuantity(Long productId, Integer quantity) {
        Product product = productRepository.find(productId)
                .orElseThrow(ProductNotFoundException::new);
        product.setQuantity(quantity);
        productRepository.update(product);
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }
}
