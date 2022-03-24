package ir.bootcamp.oneline.shop.exceptions;

public class WrongPasswordException extends RuntimeException {
    public WrongPasswordException() {
        super("password is not correct");
    }

    public WrongPasswordException(String message) {
        super(message);
    }
}
