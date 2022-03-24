package ir.bootcamp.oneline.shop.repository;

import ir.bootcamp.oneline.shop.model.Admin;

import java.util.Optional;

public interface AdminRepository extends RepositoryInterface<Admin> {
    Optional<Admin> findByUserName(String username);
}
