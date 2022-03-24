package ir.bootcamp.oneline.shop.exceptions;

public class CartIsEmptyException extends RuntimeException {
    public CartIsEmptyException() {
        super("cart is empty");
    }

    public CartIsEmptyException(String message) {
        super(message);
    }
}
