package exception;

public class UserAlreadyExistsException extends SignupException {
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
