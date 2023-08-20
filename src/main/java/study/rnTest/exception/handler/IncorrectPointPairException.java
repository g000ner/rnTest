package study.rnTest.exception.handler;

public class IncorrectPointPairException extends Exception {
    public IncorrectPointPairException() {
        super();
    }

    public IncorrectPointPairException(String message) {
        super(message);
    }

    public IncorrectPointPairException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectPointPairException(Throwable cause) {
        super(cause);
    }

    protected IncorrectPointPairException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
