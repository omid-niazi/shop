package ir.bootcamp.oneline.shop.repository.impl;

import ir.bootcamp.oneline.shop.model.Admin;
import ir.bootcamp.oneline.shop.model.Customer;
import ir.bootcamp.oneline.shop.repository.AdminRepository;
import ir.bootcamp.oneline.shop.repository.CustomerRepository;
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

class CustomerRepositoryImplTest {

    private static CustomerRepository underTest;
    private Customer sampleModel;
    private Session session;

    @BeforeAll
    static void beforeAdll() {
        underTest = new CustomerRepositoryImpl();
    }

    @BeforeEach
    void setUp() {
        sampleModel = new Customer("ap", "cp", 1000L, "address", new ArrayList<>());
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
        Optional<Customer> actual = underTest.find(sampleModel.getId());
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
        Optional<Customer> actual = underTest.find(sampleModel.getId());
        session.getTransaction().commit();

        assertThat(actual.get())
                .isEqualTo(sampleModel);
    }

    @Test
    void findAll() {
        session.beginTransaction();
        underTest.save(new Customer("customer1", "password1", 1000L, "address", new ArrayList<>()));
        underTest.save(new Customer("customer2", "password2", 1000L, "address", new ArrayList<>()));
        underTest.save(new Customer("customer3", "password3", 1000L, "address", new ArrayList<>()));
        session.getTransaction().commit();

        session.beginTransaction();
        List<Customer> actual = underTest.findAll();
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
        List<Customer> all = underTest.findAll();
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
        sampleModel.setUsername("newusername");
        underTest.update(sampleModel);
        session.getTransaction().commit();

        session.beginTransaction();
        Optional<Customer> actual = underTest.find(sampleModel.getId());
        session.getTransaction().commit();
        assertThat(actual.get()).isEqualTo(sampleModel);
    }

    @Test
    void findByUserName() {
        session.beginTransaction();
        underTest.save(sampleModel);
        session.getTransaction().commit();

        session.beginTransaction();
        Optional<Customer> actual = underTest.findByUserName(sampleModel.getUsername());
        session.getTransaction().commit();

        assertThat(actual.get())
                .isEqualTo(sampleModel);
    }
}