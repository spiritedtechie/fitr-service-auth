package resource.model;

public class SessionDto {

    private String authToken;

    public SessionDto(String authToken) {
        this.authToken = authToken;
    }

    public String getAuthToken() {
        return authToken;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public static SessionDto from(String token) {
        return new SessionDto(token);
    }
}
