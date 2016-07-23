package auth;

import com.google.common.base.Optional;
import dao.UserDao;
import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import model.User;
import org.apache.commons.lang3.StringUtils;

public class AuthTokenAuthenticator implements Authenticator<String, User> {

    private String key;
    private UserDao userDao;

    public AuthTokenAuthenticator(String key, UserDao userDao) {
        this.key = key;
        this.userDao = userDao;
    }

    @Override
    public Optional<User> authenticate(String authToken) throws AuthenticationException {

        Jws<Claims> claimsJws;
        try {
            claimsJws = Jwts.parser().setSigningKey(key).parseClaimsJws(authToken);
        } catch (Exception e) {
            throw new AuthenticationException(e);
        }

        if (claimsJws == null || claimsJws.getBody() == null || StringUtils.isEmpty(claimsJws.getBody().getSubject())) {
            return Optional.absent();
        }

        User user = userDao.findUser(claimsJws.getBody().getSubject());

        return Optional.fromNullable(user);
    }
}
