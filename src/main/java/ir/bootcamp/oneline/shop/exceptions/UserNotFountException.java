package ir.bootcamp.oneline.shop.exceptions;

public class UserNotFountException extends RuntimeException {
    public UserNotFountException() {
        super("user name not found");
    }

    public UserNotFountException(String message) {
        super(message);
    }
}
