package ir.bootcamp.oneline.shop.exceptions;

public class ProductNotFoundException extends RuntimeException {
    public ProductNotFoundException() {
        super("there is no product with this id");
    }

    public ProductNotFoundException(String message) {
        super(message);
    }
}
