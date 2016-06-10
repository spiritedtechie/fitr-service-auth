package service;

import dao.UserDao;
import model.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    public User create(User user) {

        User existingUser = userDao.getUserByEmail(user.getEmailAddress());

        if (existingUser != null) {
            return existingUser;
        }

        return userDao.create(user);
    }

    public User get(String emailAddress) {
        return userDao.getUserByEmail(emailAddress);
    }
}
  