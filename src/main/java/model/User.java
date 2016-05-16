package model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Set;

public class User {

    private String id;

    private String emailAddress;

    private Set<String> activities;

    @JsonProperty(value = "_id")
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getEmailAddress() {
        return emailAddress;
    }

    @JsonProperty
    public Set<String> getActivities() {
        return activities;
    }

}
