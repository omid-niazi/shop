package ir.bootcamp.oneline.shop.service;

import java.util.Optional;

public interface AdminService {
    void createCategory(String categoryName, Optional<Long> parentCategoryId);

    void createProduct(String name, Long categoryId, Long price, Integer quantity);

    void increaseProductQuantity(Long productId, Integer quantity);

    void signup(String username, String password);

    void login(String username, String password);
}
