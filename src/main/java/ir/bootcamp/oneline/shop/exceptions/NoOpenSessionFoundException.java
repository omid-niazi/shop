package ir.bootcamp.oneline.shop.exceptions;

public class NoOpenSessionFoundException extends RuntimeException {
    public NoOpenSessionFoundException() {
        super("no open session found exception");
    }

    public NoOpenSessionFoundException(String message) {
        super(message);
    }
}
