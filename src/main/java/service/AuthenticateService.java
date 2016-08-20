package service;

import model.Session;

public interface AuthenticateService {

    Session authenticate(String email, String password);

    void authenticateWithToken(String token);
}
