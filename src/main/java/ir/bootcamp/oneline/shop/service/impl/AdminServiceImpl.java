package ir.bootcamp.oneline.shop.service.impl;

import ir.bootcamp.oneline.shop.common.NonTransactionQuery;
import ir.bootcamp.oneline.shop.common.Transaction;
import ir.bootcamp.oneline.shop.exceptions.CategoryNotFoundException;
import ir.bootcamp.oneline.shop.exceptions.UserNotFountException;
import ir.bootcamp.oneline.shop.exceptions.UsernameIsTakenException;
import ir.bootcamp.oneline.shop.exceptions.WrongPasswordException;
import ir.bootcamp.oneline.shop.model.Admin;
import ir.bootcamp.oneline.shop.model.Category;
import ir.bootcamp.oneline.shop.repository.AdminRepository;
import ir.bootcamp.oneline.shop.service.AdminService;
import ir.bootcamp.oneline.shop.service.CategoryService;
import ir.bootcamp.oneline.shop.service.ProductService;
import ir.bootcamp.oneline.shop.service.AuthenticationContext;

import java.util.Optional;

public class AdminServiceImpl implements AdminService {

    private AdminRepository adminRepository;
    private CategoryService categoryService;
    private ProductService productService;

    public AdminServiceImpl(AdminRepository adminRepository, CategoryService categoryService, ProductService productService) {
        this.adminRepository = adminRepository;
        this.categoryService = categoryService;
        this.productService = productService;
    }


    @Override
    public void createCategory(String categoryName, Optional<Long> parentCategoryId) {
        categoryService.createCategory(categoryName, parentCategoryId);
    }

    @Override
    @NonTransactionQuery
    public void createProduct(String name, Long categoryId, Long price, Integer quantity) {
        Category category = categoryService.find(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
        productService.createProduct(name, category, price, quantity);
    }

    @Override
    public void increaseProductQuantity(Long productId, Integer quantity) {
        productService.increaseQuantity(productId, quantity);
    }

    @Override
    @Transaction
    public void signup(String username, String password) {
        adminRepository.findByUserName(username).ifPresent(ignored -> {
            throw new UsernameIsTakenException("this account number is already taken");
        });
        adminRepository.save(new Admin(username, password));
    }

    @Override
    @Transaction
    public void login(String username, String password) {
        adminRepository.findByUserName(username).ifPresentOrElse(admin -> {
            if (!admin.getPassword().equals(password)) {
                throw new WrongPasswordException();
            }
            AuthenticationContext.looggedIn(admin);
        }, () -> {
            throw new UserNotFountException();
        });
    }
}
