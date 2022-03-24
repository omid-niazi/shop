package ir.bootcamp.oneline.shop.exceptions;

public class AccessDeniedException extends RuntimeException {
    public AccessDeniedException() {
        super("you must be logged in");
    }

    public AccessDeniedException(String message) {
        super(message);
    }
}
