package ir.bootcamp.oneline.shop.repository.impl;

import ir.bootcamp.oneline.shop.model.Customer;
import ir.bootcamp.oneline.shop.repository.CustomerRepository;

import java.util.Optional;

public class CustomerRepositoryImpl extends BaseRepository<Customer> implements CustomerRepository {


    public CustomerRepositoryImpl() {
        super(Customer.class);
    }

    @Override
    public Optional<Customer> findByUserName(String username) {
        try {
            return Optional.ofNullable(getCurrentSession().createQuery("from Customer where username=:username", Customer.class)
                    .setParameter("username", username)
                    .getSingleResult());
        } catch (Exception ignored) {
            ignored.printStackTrace();
            return Optional.empty();
        }
    }

}
