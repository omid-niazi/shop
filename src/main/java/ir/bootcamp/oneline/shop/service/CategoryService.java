package ir.bootcamp.oneline.shop.service;

import ir.bootcamp.oneline.shop.model.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {
    List<Category> findAll();

    void createCategory(String categoryName, Optional<Long> parentCategoryId);

    Optional<Category> find(Long categoryId);
}
