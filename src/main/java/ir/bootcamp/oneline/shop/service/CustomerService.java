package ir.bootcamp.oneline.shop.service;

import ir.bootcamp.oneline.shop.exceptions.UserNotFountException;
import ir.bootcamp.oneline.shop.exceptions.UsernameIsTakenException;
import ir.bootcamp.oneline.shop.exceptions.WrongPasswordException;
import ir.bootcamp.oneline.shop.model.Orders;
import ir.bootcamp.oneline.shop.model.Product;

import java.util.List;

public interface CustomerService {
    void signUp(String username, String passwrod, Long balance, String address) throws UsernameIsTakenException;

    void login(String username, String password) throws UserNotFountException, WrongPasswordException;

    List<Orders> getOrders();

    void addItemToCart(Long productId, Integer quantity);

    List<Product> getAllProducts();

    List<Product> getProductsByCategoryId(Long categoryId);

    void increaseBalance(Long balance);

    void purchase();
}
