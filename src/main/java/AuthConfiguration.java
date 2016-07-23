import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

public class AuthConfiguration extends Configuration {

    @NotEmpty
    private String mongoHost;

    @Min(1)
    @Max(65535)
    private int mongoPort;

    @NotEmpty
    private String mongoDb;

    @NotEmpty
    private String authTokenSecret;

    @JsonProperty
    public String getMongoHost() {
        return mongoHost;
    }

    @JsonProperty
    public void setMongoHost(String mongoHost) {
        this.mongoHost = mongoHost;
    }

    @JsonProperty
    public int getMongoPort() {
        return mongoPort;
    }

    @JsonProperty
    public void setMongoPort(int mongoPort) {
        this.mongoPort = mongoPort;
    }

    @JsonProperty
    public String getMongoDb() {
        return mongoDb;
    }

    @JsonProperty
    public void setMongoDb(String mongoDb) {
        this.mongoDb = mongoDb;
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
