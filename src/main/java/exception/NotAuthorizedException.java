package exception;

public class NotAuthorizedException extends LoginException {
    public NotAuthorizedException(String message) {
        super(message);
    }
}
