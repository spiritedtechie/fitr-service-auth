package resource.model;

import model.Role;
import model.Session;

public class SessionDto {

    private String authToken;
    private Role role;

    public SessionDto(String authToken, Role role) {
        this.authToken = authToken;
        this.role = role;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public static SessionDto from(Session session) {
        return new SessionDto(session.getAuthToken(), session.getRole());
    }
}
