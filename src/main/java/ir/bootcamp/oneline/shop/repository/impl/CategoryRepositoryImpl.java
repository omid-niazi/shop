package ir.bootcamp.oneline.shop.repository.impl;

import ir.bootcamp.oneline.shop.model.Category;
import ir.bootcamp.oneline.shop.repository.CategoryRepository;
import ir.bootcamp.oneline.shop.repository.RepositoryInterface;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class CategoryRepositoryImpl extends BaseRepository<Category> implements CategoryRepository {

    public CategoryRepositoryImpl() {
        super(Category.class);
    }
}
