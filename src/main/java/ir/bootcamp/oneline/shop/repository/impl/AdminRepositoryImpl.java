package ir.bootcamp.oneline.shop.repository.impl;

import ir.bootcamp.oneline.shop.model.Admin;
import ir.bootcamp.oneline.shop.model.Customer;
import ir.bootcamp.oneline.shop.repository.AdminRepository;
import ir.bootcamp.oneline.shop.repository.RepositoryInterface;
import org.hibernate.Session;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AdminRepositoryImpl extends BaseRepository<Admin> implements AdminRepository {

    public AdminRepositoryImpl() {
        super(Admin.class);
    }

    @Override
    public Optional<Admin> findByUserName(String username) {
        try {
            return Optional.ofNullable(getCurrentSession().createQuery("from Admin where username=:username", Admin.class)
                    .setParameter("username", username)
                    .getSingleResult());
        } catch (Exception ignored) {
            return Optional.empty();
        }
    }
}
