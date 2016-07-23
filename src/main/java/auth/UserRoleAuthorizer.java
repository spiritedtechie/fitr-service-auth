package auth;

import io.dropwizard.auth.Authorizer;
import model.Role;
import model.User;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class UserRoleAuthorizer implements Authorizer<User> {

    private final static Logger LOG = LoggerFactory.getLogger(UserRoleAuthorizer.class);

    @Override
    public boolean authorize(User user, String accessedRoleText) {

        if (user == null) {
            LOG.error("Unable to authorize since user is null.");
            return false;
        } else if (user.getRole() == null) {
            LOG.error("Unable to authorize since user role is null.");
            return false;
        } else if (StringUtils.isBlank(accessedRoleText)) {
            LOG.error("Unable to authorize since role is blank.");
            return false;
        }

        Role accessedRole;
        try {
            accessedRole = Role.valueOf(accessedRoleText);
        } catch (IllegalArgumentException iae) {
            LOG.error("Unable to authorize since role " + accessedRoleText + " is not a valid role.");
            return false;
        }
        return user.getRole() == accessedRole;
    }
}
