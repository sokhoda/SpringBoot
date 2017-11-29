package exceptions;

public abstract class BackendException extends RuntimeException{
    public BackendException(String message) {
        super(message);
    }
}
