package core.exception;

public class PrestoShopException extends RuntimeException {

    public PrestoShopException(String message) {
        super(message);
    }

    public PrestoShopException(String message, Throwable cause) {
        super(message, cause);
    }
}
