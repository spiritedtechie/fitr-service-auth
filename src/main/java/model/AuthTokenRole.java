package model;

import resource.model.response.AuthTokenRoleDto;

public class AuthTokenRole {

    private String authToken;
    private Role role;

    public AuthTokenRole(String authToken, Role role) {
        this.authToken = authToken;
        this.role = role;
    }

    public static AuthTokenRoleDto from(AuthTokenRole authTokenRole) {
        return new AuthTokenRoleDto(authTokenRole.authToken, authTokenRole.role);
    }
}
