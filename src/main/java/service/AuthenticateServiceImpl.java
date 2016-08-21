package service;

import auth.PasswordHash;
import dao.UserDao;
import exception.AuthenticationException;
import exception.UserNotFoundException;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import model.Role;
import model.Session;
import model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;

public class AuthenticateServiceImpl implements AuthenticateService {

    private final static Logger LOG = LoggerFactory.getLogger(AuthenticateServiceImpl.class);

    private UserDao userDao;
    private String jwtSigningKey;

    public AuthenticateServiceImpl(UserDao userDao, String key) {
        this.userDao = userDao;
        this.jwtSigningKey = key;
    }

    @Override
    public String authenticate(String email, String password) {

        User user = userDao.findUser(email);

        if (user == null) {
            throw new UserNotFoundException("Unable to authenticate as could not find user");
        }

        boolean isValid;
        try {
            isValid = PasswordHash.validatePassword(password, user.getHash());
        } catch (Exception e) {
            throw new AuthenticationException("Unable to authenticate as compute failed", e);
        }

        if (!isValid) {
            throw new AuthenticationException("Unable to authenticate as password is incorrect");
        }

        String authToken;
        try {
            authToken = buildJwt(email, user.getRole());
        } catch (Exception e) {
            throw new AuthenticationException("Unable to authenticate as auth token build failed", e);
        }

        return authToken;
    }

    @Override
    public void authenticateWithToken(String token) {
        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parser().setSigningKey(jwtSigningKey).parseClaimsJws(token);
        } catch (Exception e) {
            throw new AuthenticationException("Unable to authenticate as token is invalid", e);
        }

        if (claimsJws == null || claimsJws.getBody() == null || StringUtils.isEmpty(claimsJws.getBody().getSubject())) {
            throw new AuthenticationException("Unable to authenticate as token is invalid");
        }
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
