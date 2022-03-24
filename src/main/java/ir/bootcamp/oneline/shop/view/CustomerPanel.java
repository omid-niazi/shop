package ir.bootcamp.oneline.shop.view;

import ir.bootcamp.oneline.shop.exceptions.*;
import ir.bootcamp.oneline.shop.model.Product;
import ir.bootcamp.oneline.shop.service.AuthenticationContext;
import ir.bootcamp.oneline.shop.service.CustomerService;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import static ir.bootcamp.oneline.shop.common.ConsoleMessageType.*;
import static ir.bootcamp.oneline.shop.common.ConsoleUtil.print;

public class CustomerPanel {
    private CustomerService customerService;
    private Scanner scanner = new Scanner(System.in);

    public CustomerPanel(CustomerService customerService) {
        this.customerService = customerService;
    }


    public void showMenu() {
        while (true) {
            System.out.println("1- increase balance");
            System.out.println("2- show all products");
            System.out.println("3- show products by category");
            System.out.println("4- add item to cart");
            System.out.println("5- confirm purchase");
            System.out.println("6- show my orders");
            System.out.println("0- logout");
            int key = getKey();
            scanner.nextLine();
            switch (key) {
                case 1:
                    increaseBalance();
                    break;
                case 2:
                    showAllProducts();
                    break;
                case 3:
                    showProductsByCategory();
                    break;
                case 4:
                    addProductToCart();
                    break;
                case 5:
                    confirmPurchase();
                    break;
                case 6:
                    showMyOrders();
                    break;
                case 0:
                    logout();
                    return;
                case -1:
                    System.out.println("wrong command");
                    break;
            }
        }
    }

    private void showMyOrders() {
        customerService.getOrders()
                .forEach(orders -> print(orders.toString(), info));
    }

    private void increaseBalance() {
        System.out.println("balance: ");
        Long balance = scanner.nextLong();
        customerService.increaseBalance(balance);
    }

    private void showAllProducts() {
        customerService.getAllProducts()
                .forEach(product -> print(product.toString(), info));
    }

    private void showProductsByCategory() {
        System.out.println("Category Id: ");
        Long categoryId = scanner.nextLong();
        try {
            customerService.getProductsByCategoryId(categoryId)
                    .forEach(product -> print(product.toString(), info));
        } catch (CategoryNotFoundException e) {
            print(e.getMessage(), error);
        }
    }

    private void addProductToCart() {
        System.out.println("product id: ");
        Long productId = scanner.nextLong();
        System.out.println("quantity: ");
        Integer quantity = scanner.nextInt();
        try {
            customerService.addItemToCart(productId, quantity);
            print("item added to cart", success);
        } catch (ProductOutOfQuantityException | ProductNotFoundException | BalanceIsNotEnoughException e) {
            print(e.getMessage(), error);
        }
    }

    private void confirmPurchase() {
        try {
            customerService.purchase();
            print("your order purchased", success);
        } catch (CartIsEmptyException e) {
            print(e.getMessage(), error);
        }
    }

    private void logout() {
        AuthenticationContext.logout();
    }

    private int getKey() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            return -1;
        }
    }
}
