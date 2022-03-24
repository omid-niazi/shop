package ir.bootcamp.oneline.shop.service.impl;

import ir.bootcamp.oneline.shop.common.Transaction;
import ir.bootcamp.oneline.shop.exceptions.*;
import ir.bootcamp.oneline.shop.model.*;
import ir.bootcamp.oneline.shop.repository.CustomerRepository;
import ir.bootcamp.oneline.shop.service.*;
import org.aspectj.weaver.ast.Or;

import javax.persistence.criteria.Order;
import java.util.ArrayList;
import java.util.List;

public class CustomerServiceImpl implements CustomerService {
    private OrderService orderService;
    private CustomerRepository customerRepository;
    private ProductService productService;
    private CategoryService categoryService;
    private List<OrderItem> orderItems = new ArrayList<>();

    public CustomerServiceImpl(OrderService orderService, CustomerRepository customerRepository, ProductService productService, CategoryService categoryService) {
        this.orderService = orderService;
        this.customerRepository = customerRepository;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    @Transaction
    public void signUp(String username, String passwrod, Long balance, String address) {
        customerRepository.findByUserName(username).ifPresent(ignored -> {
            throw new UsernameIsTakenException("this account number is already taken");
        });
        customerRepository.save(new Customer(username, passwrod, balance, address, null));
    }

    @Override
    @Transaction
    public void login(String username, String password) {
        customerRepository.findByUserName(username).ifPresentOrElse(user -> {
            if (!user.getPassword().equals(password)) {
                throw new WrongPasswordException();
            }
            AuthenticationContext.looggedIn(user);
        }, () -> {
            throw new UserNotFountException();
        });
    }

    @Override
    public List<Orders> getOrders() {
        return orderService.findUserOrders((Customer) AuthenticationContext.getLoggedInUser());
    }

    @Override
    @Transaction
    public void addItemToCart(Long productId, Integer quantity) {
        Product product = productService.find(productId);
        if (product.getQuantity() < quantity) {
            throw new ProductOutOfQuantityException();
        }

        Customer loggedInUser = (Customer) AuthenticationContext.getLoggedInUser();
        if (loggedInUser.getBalance() < product.getPrice() * quantity) {
            throw new BalanceIsNotEnoughException();
        }

        orderItems.add(new OrderItem(product, quantity));
        loggedInUser.setBalance(loggedInUser.getBalance() - product.getPrice() * quantity);
    }

    @Override
    public List<Product> getAllProducts() {
        return productService.findAll();
    }

    @Override
    @Transaction
    public List<Product> getProductsByCategoryId(Long categoryId) {
        Category category = categoryService.find(categoryId)
                .orElseThrow(CategoryNotFoundException::new);
        return productService.findByCategory(category);
    }

    @Override
    @Transaction
    public void increaseBalance(Long balance) {
        Customer loggedInUser = (Customer) AuthenticationContext.getLoggedInUser();
        loggedInUser.setBalance(loggedInUser.getBalance() + balance);
        customerRepository.update(loggedInUser);
    }

    @Override
    @Transaction
    public void purchase() {
        if (orderItems.size() == 0) {
            throw new CartIsEmptyException();
        }
        Customer loggedInUser = (Customer) AuthenticationContext.getLoggedInUser();
        Long price = orderItems.stream()
                .mapToLong(orderItem -> orderItem.getProduct().getPrice() * orderItem.getQuantity())
                .sum();
        Orders order = new Orders(true, loggedInUser, orderItems, price);
        orderItems.forEach(orderItem -> {
            Product p = orderItem.getProduct();
            p.setQuantity(p.getQuantity() - orderItem.getQuantity());
            productService.update(p);
        });
        orderService.create(order);
        customerRepository.update(loggedInUser);
        AuthenticationContext.looggedIn(customerRepository.find(loggedInUser.getId()).get());
    }
}
