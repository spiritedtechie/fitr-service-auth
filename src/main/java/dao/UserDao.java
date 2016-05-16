package dao;

import model.User;

public interface UserDao {

    User create(User user);

    User getUserByEmail(String emailAddress);
}
