package model;

public class Session {

    private String authToken;
    private Role role;

    public Session(String authToken, Role role) {
        this.authToken = authToken;
        this.role = role;
    }

    public String getAuthToken() {
        return authToken;
    }

    public Role getRole() {
        return role;
    }
}
