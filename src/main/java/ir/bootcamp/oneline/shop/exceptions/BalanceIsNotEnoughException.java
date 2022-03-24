package ir.bootcamp.oneline.shop.exceptions;

public class BalanceIsNotEnoughException extends RuntimeException {
    public BalanceIsNotEnoughException() {
        super("your account balance is not enough");
    }

    public BalanceIsNotEnoughException(String message) {
        super(message);
    }
}
