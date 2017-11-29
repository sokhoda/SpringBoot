package exceptions;

public class ViewNotFoundException extends BackendException {
    public ViewNotFoundException(String message) {
        super(message);
    }
}
