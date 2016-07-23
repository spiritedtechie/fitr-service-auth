package model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.security.Principal;

public class User implements Principal {

    private String id;

    private String email;

    private String hash;

    private Role role = Role.NORMAL;

    public User() {
    }

    public User(String email, String hash) {
        this.email = email;
        this.hash = hash;
    }

    @JsonProperty(value = "_id")
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getEmail() {
        return email;
    }

    @JsonProperty
    public String getHash() {
        return hash;
    }

    @JsonProperty
    public Role getRole() {
        return role;
    }

    @Override
    @JsonIgnore
    public String getName() {
        return email;
    }
}
