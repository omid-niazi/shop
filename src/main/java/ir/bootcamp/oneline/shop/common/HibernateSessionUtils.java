package ir.bootcamp.oneline.shop.common;

import ir.bootcamp.oneline.shop.exceptions.NoOpenSessionFoundException;
import ir.bootcamp.oneline.shop.model.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class HibernateSessionUtils {
    private static SessionFactory sessionFactory;
    private static Session session;

    public static SessionFactory getSessionFactory() {

        if (sessionFactory == null) {
            StandardServiceRegistry build = new StandardServiceRegistryBuilder()
                    .configure()
                    .build();
            sessionFactory = new MetadataSources(build)
                    .addAnnotatedClass(Admin.class)
                    .addAnnotatedClass(Category.class)
                    .addAnnotatedClass(Product.class)
                    .addAnnotatedClass(Orders.class)
                    .addAnnotatedClass(OrderItem.class)
                    .addAnnotatedClass(Customer.class)
                    .addAnnotatedClass(User.class)
                    .buildMetadata().buildSessionFactory();
        }
        return sessionFactory;
    }

    public static Session getCurrentSession() {
        if (session == null || !session.isOpen()) {
            throw new NoOpenSessionFoundException();
        }
        return session;
    }

    public static Session openSession() {
        if (session != null) {
            session.close();
            session = null;
        }
        return session = getSessionFactory().openSession();
    }

}
