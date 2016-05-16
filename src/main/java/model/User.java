package model;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.mongojack.ObjectId;

import java.util.Set;

public class User {

    @ObjectId
    private String id;

    private String emailAddress;

    private Set<Long> activities;

    @ObjectId
    @JsonProperty(value = "_id")
    public String getId() {
        return id;
    }

    @JsonProperty
    public String getEmailAddress() {
        return emailAddress;
    }

    @JsonProperty
    public Set<Long> getActivities() {
        return activities;
    }

    @JsonProperty(value = "_id")
    public void setId(String id) {
        this.id = id;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setActivities(Set<Long> activities) {
        this.activities = activities;
    }
}
