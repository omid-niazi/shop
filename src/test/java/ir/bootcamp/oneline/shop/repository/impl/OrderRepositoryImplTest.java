package ir.bootcamp.oneline.shop.repository.impl;

import ir.bootcamp.oneline.shop.model.Admin;
import ir.bootcamp.oneline.shop.model.Customer;
import ir.bootcamp.oneline.shop.model.Orders;
import ir.bootcamp.oneline.shop.repository.AdminRepository;
import ir.bootcamp.oneline.shop.repository.OrderRepository;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ir.bootcamp.oneline.shop.common.HibernateSessionUtils.openSession;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class OrderRepositoryImplTest {

    private static OrderRepository underTest;
    private Orders sampleModel;
    private Session session;

    @BeforeAll
    static void beforeAdll() {
        underTest = new OrderRepositoryImpl();
    }

    @BeforeEach
    void setUp() {
        sampleModel = new Orders(true, new Customer("c1", "p1", 1000L, "add", new ArrayList<>()), new ArrayList<>(), 1000L);
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
        Optional<Orders> actual = underTest.find(sampleModel.getId());
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
        Optional<Orders> actual = underTest.find(sampleModel.getId());
        session.getTransaction().commit();

        assertThat(actual.get())
                .isEqualTo(sampleModel);
    }

    @Test
    void findAll() {
        session.beginTransaction();
        underTest.save(new Orders(true, new Customer("c1", "p1", 1000L, "add", new ArrayList<>()), new ArrayList<>(), 1000L));
        underTest.save(new Orders(true, new Customer("c1", "p1", 1000L, "add", new ArrayList<>()), new ArrayList<>(), 1000L));
        underTest.save(new Orders(true, new Customer("c1", "p1", 1000L, "add", new ArrayList<>()), new ArrayList<>(), 1000L));
        session.getTransaction().commit();

        session.beginTransaction();
        List<Orders> actual = underTest.findAll();
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
        List<Orders> all = underTest.findAll();
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
        sampleModel.setFinal(false);
        underTest.update(sampleModel);
        session.getTransaction().commit();

        session.beginTransaction();
        Optional<Orders> actual = underTest.find(sampleModel.getId());
        session.getTransaction().commit();
        assertThat(actual.get()).isEqualTo(sampleModel);
    }

    @Test
    void findByUser() {
        session.beginTransaction();
        Customer user = new Customer("c1", "p1", 1000L, "add", new ArrayList<>());
        underTest.save(new Orders(true, user, new ArrayList<>(), 1000L));
        underTest.save(new Orders(true, user, new ArrayList<>(), 1000L));
        session.getTransaction().commit();

        session.beginTransaction();
        List<Orders> actual = underTest.findByUser(user);
        session.getTransaction().commit();

        assertThat(actual.size())
                .isEqualTo(2);
    }
}