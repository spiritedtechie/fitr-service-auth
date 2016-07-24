import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class AuthConfiguration extends Configuration {

    @Valid
    @NotNull
    private DatabaseConfiguration database = new DatabaseConfiguration();

    @NotNull
    @NotEmpty
    private String authTokenSecret;

    @JsonProperty("database")
    public DatabaseConfiguration getDatabase() {
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DatabaseConfiguration datbaseConfiguration) {
        this.database = datbaseConfiguration;
    }

    @JsonProperty
    public String getAuthTokenSecret() {
        return authTokenSecret;
    }

    @JsonProperty
    public void setAuthTokenSecret(String authTokenSecret) {
        this.authTokenSecret = authTokenSecret;
    }
}
