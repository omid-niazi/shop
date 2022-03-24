package ir.bootcamp.oneline.shop.repository.impl;

import ir.bootcamp.oneline.shop.model.Admin;
import ir.bootcamp.oneline.shop.model.Category;
import ir.bootcamp.oneline.shop.model.Product;
import ir.bootcamp.oneline.shop.repository.AdminRepository;
import ir.bootcamp.oneline.shop.repository.ProductRepository;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static ir.bootcamp.oneline.shop.common.HibernateSessionUtils.openSession;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryImplTest {

    private static ProductRepository underTest;
    private Product sampleModel;
    private Session session;

    @BeforeAll
    static void beforeAdll() {
        underTest = new ProductRepositoryImpl();
    }

    @BeforeEach
    void setUp() {
        sampleModel = new Product("p", new Category("c", null), 1000L, 100);
        session = openSession();
        session.beginTransaction();
        underTest.deleteAll();
        session.getTransaction().commit();
    }

    @AfterEach
    void tearDown() {
        if (session != null && session.isOpen()) {
            session.close();
        }
    }

    @Test
    void save() {
        session.beginTransaction();
        underTest.save(sampleModel);
        session.getTransaction().commit();

        session.beginTransaction();
        Optional<Product> actual = underTest.find(sampleModel.getId());
        session.getTransaction().commit();

        assertThat(actual.get())
                .isEqualTo(sampleModel);
    }

    @Test
    void find() {
        session.beginTransaction();
        underTest.save(sampleModel);
        session.getTransaction().commit();

        session.beginTransaction();
        Optional<Product> actual = underTest.find(sampleModel.getId());
        session.getTransaction().commit();

        assertThat(actual.get())
                .isEqualTo(sampleModel);
    }

    @Test
    void findAll() {
        session.beginTransaction();
        Category category = new Category("c", null);
        underTest.save(new Product("p", category, 1000L, 100));
        underTest.save(new Product("p", category, 1000L, 100));
        underTest.save(new Product("p", category, 1000L, 100));
        session.getTransaction().commit();

        session.beginTransaction();
        List<Product> actual = underTest.findAll();
        session.getTransaction().commit();

        assertThat(actual.size())
                .isEqualTo(3);
    }

    @Test
    void delete() {
        session.beginTransaction();
        underTest.save(sampleModel);
        session.getTransaction().commit();

        session.beginTransaction();
        underTest.delete(sampleModel);
        session.getTransaction().commit();

        session.beginTransaction();
        List<Product> all = underTest.findAll();
        session.getTransaction().commit();
        assertThat(all.size())
                .isEqualTo(0);
    }

    @Test
    void update() {
        session.beginTransaction();
        underTest.save(sampleModel);
        session.getTransaction().commit();

        session.beginTransaction();
        sampleModel.setName("p3");
        underTest.update(sampleModel);
        session.getTransaction().commit();

        session.beginTransaction();
        Optional<Product> actual = underTest.find(sampleModel.getId());
        session.getTransaction().commit();
        assertThat(actual.get()).isEqualTo(sampleModel);
    }

    @Test
    void findByCategory() {
        session.beginTransaction();
        Category category = new Category("c", null);
        underTest.save(new Product("p", category, 1000L, 100));
        underTest.save(new Product("p", category, 1000L, 100));
        underTest.save(new Product("p", new Category("c3", null), 1000L, 100));
        session.getTransaction().commit();

        session.beginTransaction();
        List<Product> actual = underTest.findByCategory(category);
        session.getTransaction().commit();

        assertThat(actual.size())
                .isEqualTo(2);
    }
}