package ir.bootcamp.oneline.shop.repository;

import ir.bootcamp.oneline.shop.model.Admin;
import ir.bootcamp.oneline.shop.model.Category;
import ir.bootcamp.oneline.shop.model.Product;

import java.util.List;

public interface ProductRepository extends RepositoryInterface<Product> {
    List<Product> findByCategory(Category category);
}
