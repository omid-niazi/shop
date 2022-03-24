package ir.bootcamp.oneline.shop.exceptions;

public class ProductOutOfQuantityException extends RuntimeException {
    public ProductOutOfQuantityException() {
        super("product quantity is not enough");
    }

    public ProductOutOfQuantityException(String message) {
        super(message);
    }
}
