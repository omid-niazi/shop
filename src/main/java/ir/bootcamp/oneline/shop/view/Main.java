package ir.bootcamp.oneline.shop.view;

import ir.bootcamp.oneline.shop.exceptions.UserNotFountException;
import ir.bootcamp.oneline.shop.exceptions.UsernameIsTakenException;
import ir.bootcamp.oneline.shop.exceptions.WrongPasswordException;
import ir.bootcamp.oneline.shop.repository.AdminRepository;
import ir.bootcamp.oneline.shop.repository.impl.*;
import ir.bootcamp.oneline.shop.service.AdminService;
import ir.bootcamp.oneline.shop.service.CategoryService;
import ir.bootcamp.oneline.shop.service.ProductService;
import ir.bootcamp.oneline.shop.service.CustomerService;
import ir.bootcamp.oneline.shop.service.impl.*;

import java.util.Scanner;

import static ir.bootcamp.oneline.shop.common.ConsoleMessageType.*;
import static ir.bootcamp.oneline.shop.common.ConsoleUtil.*;

public class Main {

    private static Scanner scanner = new Scanner(System.in);
    private static CustomerService customerService;
    private static AdminService adminService;

    public static void main(String[] args) {
        AdminRepository adminRepository = new AdminRepositoryImpl();
        CategoryRepositoryImpl categoryRepository = new CategoryRepositoryImpl();
        CategoryService categoryService = new CategoryServiceImpl(categoryRepository);
        ProductRepositoryImpl productRepository = new ProductRepositoryImpl();
        ProductService productService = new ProductServiceImpl(productRepository);
        CustomerRepositoryImpl customerRepository = new CustomerRepositoryImpl();
        OrderRepositoryImpl orderRepository = new OrderRepositoryImpl();
        OrderServiceImpl orderService = new OrderServiceImpl(orderRepository);
        customerService = new CustomerServiceImpl(orderService, customerRepository, productService, categoryService);
        adminService = new AdminServiceImpl(adminRepository, categoryService, productService);
        mainMenu();
    }

    private static void mainMenu() {
        boolean finished = false;
        while (!finished) {
            System.out.println("1- user signup");
            System.out.println("2- user login");
            System.out.println("3- admin signup");
            System.out.println("4- admin login");
            int key = getKey();
            scanner.nextLine();
            switch (key) {
                case 1:
                    userSignup();
                    break;
                case 2:
                    userLogin();
                    break;
                case 0:
                    finished = true;
                    break;
                case 3:
                    adminSignup();
                    break;
                case 4:
                    adminLogin();
                    break;
                case -1:
                    System.out.println("wrong command");
            }
        }
    }

    private static void adminSignup() {
        System.out.println("username: ");
        String username = scanner.nextLine();
        System.out.println("password: ");
        String password = scanner.nextLine();
        try {
            adminService.signup(username, password);
            print("signed up successfully", success);
        } catch (UsernameIsTakenException e) {
            print(e.getMessage(), error);
        }
    }

    private static void adminLogin() {
        System.out.println("username: ");
        String username = scanner.nextLine();
        System.out.println("password: ");
        String password = scanner.nextLine();
        try {
            adminService.login(username, password);
            new AdminPanel(adminService)
                    .showMenu();
        } catch (UserNotFountException | WrongPasswordException e) {
            print(e.getMessage(), error);
        }
    }

    private static void userSignup() {
        System.out.println("username: ");
        String username = scanner.nextLine();
        System.out.println("password: ");
        String password = scanner.nextLine();
        System.out.println("address: ");
        String address = scanner.nextLine();
        try {
            customerService.signUp(username, password, 0L, address);
            print("account created", success);
        } catch (UsernameIsTakenException e) {
            print(e.getMessage(), error);
        }
    }

    private static void userLogin() {
        System.out.println("username: ");
        String username = scanner.nextLine();
        System.out.println("password: ");
        String password = scanner.nextLine();
        try {
            customerService.login(username, password);
            print("logged in successfully", success);
            new CustomerPanel(customerService)
                    .showMenu();
        } catch (UserNotFountException | WrongPasswordException e) {
            print(e.getMessage(), error);
        }
    }

    private static int getKey() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            return -1;
        }
    }
}
