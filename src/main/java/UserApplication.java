import com.mongodb.DB;
import com.mongodb.MongoClient;
import config.UserConfiguration;
import dao.UserDao;
import dao.UserDaoImpl;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import resource.UserResource;

import java.net.UnknownHostException;

public class UserApplication extends Application<UserConfiguration> {

    public static void main(String[] args) throws Exception {
        new UserApplication().run(args);
    }

    @Override
    public String getName() {
        return "user-service";
    }

    public void run(UserConfiguration configuration,
                    Environment environment) throws Exception {

        DB db = setupMongoDB(configuration, environment);

        UserDao dao = new UserDaoImpl(db);

        environment.jersey().register(new UserResource(dao));
    }

    private DB setupMongoDB(UserConfiguration configuration, Environment environment) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(configuration.getMongoHost(), configuration.getMongoPort());
        DB db = mongoClient.getDB(configuration.getMongoDb());
        MongoManaged mongoManaged = new MongoManaged(mongoClient);
        environment.lifecycle().manage(mongoManaged);
        return db;
    }
}
