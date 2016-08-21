package service;

import model.Session;

public interface AuthenticateService {

    String authenticate(String email, String password);

    void authenticateWithToken(String token);
}
