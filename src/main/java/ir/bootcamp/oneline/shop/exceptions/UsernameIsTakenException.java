package ir.bootcamp.oneline.shop.exceptions;

public class UsernameIsTakenException extends RuntimeException{
    public UsernameIsTakenException() {
    }

    public UsernameIsTakenException(String message) {
        super(message);
    }
}
