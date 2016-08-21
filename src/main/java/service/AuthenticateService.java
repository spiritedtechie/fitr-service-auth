package service;

public interface AuthenticateService {

    String authenticate(String email, String password);

    void authenticateWithToken(String token);
}
