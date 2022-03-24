package ir.bootcamp.oneline.shop.service.impl;

import ir.bootcamp.oneline.shop.common.Transaction;
import ir.bootcamp.oneline.shop.exceptions.CategoryNotFoundException;
import ir.bootcamp.oneline.shop.model.Category;
import ir.bootcamp.oneline.shop.repository.CategoryRepository;
import ir.bootcamp.oneline.shop.service.CategoryService;

import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {
    private CategoryRepository categoryRepository;

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }


    @Override
    @Transaction
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    @Transaction
    public void createCategory(String categoryName, Optional<Long> parentCategoryId) {
        Category category = new Category();
        category.setName(categoryName);
        parentCategoryId.ifPresent(id -> {
            category.setParentCategory(categoryRepository.find(parentCategoryId.get())
                    .orElseThrow(() -> new CategoryNotFoundException("parent category doesn't exists")));
        });
        categoryRepository.save(category);
    }

    @Override
    public Optional<Category> find(Long categoryId) {
        return categoryRepository.find(categoryId);
    }
}
