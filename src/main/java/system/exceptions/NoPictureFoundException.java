package system.exceptions;

public class NoPictureFoundException extends Throwable {
    public NoPictureFoundException(){super();}
    public NoPictureFoundException(String message) {
        super(message);
    }
}
