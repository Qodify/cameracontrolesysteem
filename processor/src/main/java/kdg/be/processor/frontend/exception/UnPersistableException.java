package kdg.be.processor.frontend.exception;

public class UnPersistableException extends Exception {
    public UnPersistableException(String message) {
        super(message);
    }

    public UnPersistableException(Throwable cause) {
        super(cause);
    }
}
