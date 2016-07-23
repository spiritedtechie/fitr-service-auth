package service;

import model.AuthTokenRole;

public interface LoginService {

    AuthTokenRole login(String email, String password);
}
