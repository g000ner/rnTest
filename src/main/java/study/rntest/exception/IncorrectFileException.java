package study.rntest.exception;

public class IncorrectFileException extends Exception {
    public IncorrectFileException() {
        super();
    }

    public IncorrectFileException(String message) {
        super(message);
    }

    public IncorrectFileException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectFileException(Throwable cause) {
        super(cause);
    }

    protected IncorrectFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
