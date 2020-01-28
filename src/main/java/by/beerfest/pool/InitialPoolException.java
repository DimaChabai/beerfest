package by.beerfest.pool;

public class InitialPoolException extends Exception {
    public InitialPoolException() {
    }

    public InitialPoolException(String message) {
        super(message);
    }

    public InitialPoolException(String message, Throwable cause) {
        super(message, cause);
    }

    public InitialPoolException(Throwable cause) {
        super(cause);
    }

    public InitialPoolException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
