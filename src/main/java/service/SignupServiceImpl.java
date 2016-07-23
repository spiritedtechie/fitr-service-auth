package service;

import auth.PasswordHash;
import dao.UserDao;
import exception.SignupException;
import exception.UserAlreadyExistsException;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SignupServiceImpl implements SignupService {

    private final static Logger LOG = LoggerFactory.getLogger(SignupServiceImpl.class);

    private final UserDao userDao;

    public SignupServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void signup(String email, String password) {

        if (userDao.findUser(email) != null) {
            throw new UserAlreadyExistsException("User already exists");
        }

        String hash;
        try {
            hash = PasswordHash.createHash(password);
        } catch (Exception e) {
            throw new SignupException("Signup failed during hash creation");
        }

        try {
            userDao.create(new User(email, hash));
        } catch (Exception e) {
            throw new SignupException("Signup failed as failure creating user");
        }
    }

}
