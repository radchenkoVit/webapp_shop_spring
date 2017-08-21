package system.exceptions;

public class ApplicationExistsException extends Throwable {
    public ApplicationExistsException(String message) {
        super(message);
    }
}
