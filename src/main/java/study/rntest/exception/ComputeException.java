package study.rntest.exception;

public class ComputeException extends Exception {
    public ComputeException() {
    }

    public ComputeException(String message) {
        super(message);
    }

    public ComputeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ComputeException(Throwable cause) {
        super(cause);
    }

    public ComputeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
