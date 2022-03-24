package ir.bootcamp.oneline.shop.repository;

import ir.bootcamp.oneline.shop.model.Customer;

import java.util.Optional;

public interface CustomerRepository extends RepositoryInterface<Customer> {
    Optional<Customer> findByUserName(String username);
}
