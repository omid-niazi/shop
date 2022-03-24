package ir.bootcamp.oneline.shop.repository.impl;

import ir.bootcamp.oneline.shop.model.Admin;
import ir.bootcamp.oneline.shop.model.Category;
import ir.bootcamp.oneline.shop.model.OrderItem;
import ir.bootcamp.oneline.shop.model.Product;
import ir.bootcamp.oneline.shop.repository.AdminRepository;
import ir.bootcamp.oneline.shop.repository.OrderItemRepository;
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

class OrderItemRepositoryImplTest {
    private static OrderItemRepository underTest;
    private OrderItem sampleModel;
    private Session session;

    @BeforeAll
    static void beforeAdll() {
        underTest = new OrderItemRepositoryImpl();
    }

    @BeforeEach
    void setUp() {
        sampleModel = new OrderItem(new Product("pname", new Category("ch", null), 1000L, 10), 10);
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
        Optional<OrderItem> actual = underTest.find(sampleModel.getId());
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
        Optional<OrderItem> actual = underTest.find(sampleModel.getId());
        session.getTransaction().commit();

        assertThat(actual.get())
                .isEqualTo(sampleModel);
    }

    @Test
    void findAll() {
        session.beginTransaction();
        underTest.save(new OrderItem(new Product("pname", new Category("ch", null), 1000L, 10), 10));
        underTest.save(new OrderItem(new Product("pname1", new Category("ch1", null), 1000L, 10), 10));
        underTest.save(new OrderItem(new Product("pname2", new Category("ch2", null), 1000L, 10), 10));
        session.getTransaction().commit();

        session.beginTransaction();
        List<OrderItem> actual = underTest.findAll();
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
        List<OrderItem> all = underTest.findAll();
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
        sampleModel.setQuantity(20);
        underTest.update(sampleModel);
        session.getTransaction().commit();

        session.beginTransaction();
        Optional<OrderItem> actual = underTest.find(sampleModel.getId());
        session.getTransaction().commit();
        assertThat(actual.get()).isEqualTo(sampleModel);
    }


}