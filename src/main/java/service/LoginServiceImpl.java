package service;

import auth.PasswordHash;
import dao.UserDao;
import exception.LoginException;
import exception.NotAuthorizedException;
import exception.UserNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.AuthTokenRole;
import model.Role;
import model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class LoginServiceImpl implements LoginService {

    private final static Logger LOG = LoggerFactory.getLogger(LoginServiceImpl.class);

    private UserDao userDao;
    private String jwtSigningKey;

    public LoginServiceImpl(UserDao userDao, String key) {
        this.userDao = userDao;
        this.jwtSigningKey = key;
    }

    @Override
    public AuthTokenRole login(String email, String password) {

        User user = userDao.findUser(email);

        if (user == null) {
            throw new UserNotFoundException("Unable to login as could not find user");
        }

        boolean isValid;
        try {
            isValid = PasswordHash.validatePassword(password, user.getHash());
        } catch (Exception e) {
            throw new LoginException("Unable to login as compute failed", e);
        }

        if (!isValid) {
            throw new NotAuthorizedException("Unable to login as password is incorrect");
        }

        String authToken;
        try {
            authToken = buildJwt(email, user.getRole());
        } catch (Exception e) {
            throw new LoginException("Unable to login as auth token build failed", e);
        }

        return new AuthTokenRole(authToken, user.getRole());
    }

    private String buildJwt(String email, Role role) {
        Claims claims = Jwts.claims().setSubject(email);
        claims.put("role", role.toString());

        return Jwts.builder()
                .setExpiration(oneDayFromNow())
                .setIssuedAt(now())
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, jwtSigningKey)
                .compact();
    }

    private Date now() {
        return new Date();
    }

    private Date oneDayFromNow() {
        Calendar now = Calendar.getInstance();
        now.add(Calendar.HOUR, 24);
        return now.getTime();
    }

}
