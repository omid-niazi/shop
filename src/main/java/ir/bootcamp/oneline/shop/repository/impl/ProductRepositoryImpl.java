package ir.bootcamp.oneline.shop.repository.impl;

import ir.bootcamp.oneline.shop.model.Category;
import ir.bootcamp.oneline.shop.model.Product;
import ir.bootcamp.oneline.shop.repository.ProductRepository;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;

public class ProductRepositoryImpl extends BaseRepository<Product> implements ProductRepository {

    public ProductRepositoryImpl() {
        super(Product.class);
    }

    @Override
    public List<Product> findByCategory(Category category) {
        try {
            return getCurrentSession().createNativeQuery("with recursive c as (" +
                            "    select category.id id, category.name, category.parentcategory_id from category" +
                            "    where id =?" +
                            "    union all" +
                            "    select category.id id, category.name, category.parentcategory_id from category" +
                            "    inner join c" +
                            "    on category.parentcategory_id = c.id" +
                            ")select * from product where category_id in (select id from c)", Product.class)
                    .setParameter(1, category.getId())
                    .getResultList();

        } catch (Exception ignored) {
            return new ArrayList<>();
        }
    }
}
