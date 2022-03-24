package ir.bootcamp.oneline.shop.view;

import ir.bootcamp.oneline.shop.exceptions.CategoryNotFoundException;
import ir.bootcamp.oneline.shop.exceptions.ProductNotFoundException;
import ir.bootcamp.oneline.shop.service.AdminService;
import ir.bootcamp.oneline.shop.service.AuthenticationContext;

import java.util.Optional;
import java.util.Scanner;

import static ir.bootcamp.oneline.shop.common.ConsoleMessageType.error;
import static ir.bootcamp.oneline.shop.common.ConsoleMessageType.success;
import static ir.bootcamp.oneline.shop.common.ConsoleUtil.print;

public class AdminPanel {
    private AdminService adminService;
    private Scanner scanner = new Scanner(System.in);

    public AdminPanel(AdminService adminService) {
        this.adminService = adminService;
    }

    public void showMenu() {
        while (true) {
            System.out.println("1- create category");
            System.out.println("2- add product");
            System.out.println("3- increase product quantity");
            System.out.println("0- logout");
            int key = getKey();
            scanner.nextLine();
            switch (key) {
                case 1:
                    createCategory();
                    break;
                case 2:
                    addProduct();
                    break;
                case 3:
                    increaseProductQuantity();
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

    private void createCategory() {
        System.out.println("category name:");
        String categoryName = scanner.nextLine();
        System.out.println("parent category id(enter if doesn't exists):");
        String parentCategoryString = scanner.nextLine();
        Optional<Long> parentCategoryId = Optional.empty();
        if (!parentCategoryString.isBlank())
            parentCategoryId = Optional.of(Long.parseLong(parentCategoryString));
        try {
            adminService.createCategory(categoryName, parentCategoryId);
            print("category created", success);
        } catch (CategoryNotFoundException e) {
            print(e.getMessage(), error);
        }
    }

    private void addProduct() {
        System.out.println("product name: ");
        String name = scanner.nextLine();
        System.out.println("category Id: ");
        Long categoryId = scanner.nextLong();
        System.out.println("price: ");
        Long price = scanner.nextLong();
        System.out.println("quantity: ");
        Integer quantity = scanner.nextInt();
        try {
            adminService.createProduct(name, categoryId, price, quantity);
            print("product created", success);
        } catch (CategoryNotFoundException e) {
            print(e.getMessage(), error);
        }
    }

    private void increaseProductQuantity() {
        System.out.println("product Id: ");
        Long productId = scanner.nextLong();
        System.out.println("quantity: ");
        Integer quantity = scanner.nextInt();
        try {
            adminService.increaseProductQuantity(productId, quantity);
            print("product quantity changed", success);
        } catch (ProductNotFoundException e) {
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
