package ir.bootcamp.oneline.shop.repository.impl;

import ir.bootcamp.oneline.shop.model.Admin;
import ir.bootcamp.oneline.shop.repository.AdminRepository;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static ir.bootcamp.oneline.shop.common.HibernateSessionUtils.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class AdminRepositoryImplTest {

    private static AdminRepository underTest;
    private Admin sampleModel;
    private Session session;

    @BeforeAll
    static void beforeAdll() {
        underTest = new AdminRepositoryImpl();
    }

    @BeforeEach
    void setUp() {
        sampleModel = new Admin("adminusername", "adminpassword");
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
        Optional<Admin> actual = underTest.find(sampleModel.getId());
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
        Optional<Admin> actual = underTest.find(sampleModel.getId());
        session.getTransaction().commit();

        assertThat(actual.get())
                .isEqualTo(sampleModel);
    }

    @Test
    void findAll() {
        session.beginTransaction();
        underTest.save(new Admin("admin1", "password1"));
        underTest.save(new Admin("admin2", "password2"));
        underTest.save(new Admin("admin3", "password3"));
        session.getTransaction().commit();

        session.beginTransaction();
        List<Admin> actual = underTest.findAll();
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
        List<Admin> all = underTest.findAll();
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
        Optional<Admin> actual = underTest.find(sampleModel.getId());
        session.getTransaction().commit();
        assertThat(actual.get()).isEqualTo(sampleModel);
    }

    @Test
    void findByUserName() {
        session.beginTransaction();
        underTest.save(sampleModel);
        session.getTransaction().commit();

        session.beginTransaction();
        Optional<Admin> actual = underTest.findByUserName(sampleModel.getUsername());
        session.getTransaction().commit();

        assertThat(actual.get())
                .isEqualTo(sampleModel);
    }
}