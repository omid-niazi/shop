package ir.bootcamp.oneline.shop.exceptions;

public class CategoryNotFoundException extends RuntimeException {
    public CategoryNotFoundException() {
        super("category not found");
    }

    public CategoryNotFoundException(String message) {
        super(message);
    }
}
