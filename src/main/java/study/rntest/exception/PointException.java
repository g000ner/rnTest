package study.rntest.exception;

public class PointException extends Exception{
    public PointException() {
    }

    public PointException(String message) {
        super(message);
    }

    public PointException(String message, Throwable cause) {
        super(message, cause);
    }

    public PointException(Throwable cause) {
        super(cause);
    }

    public PointException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
