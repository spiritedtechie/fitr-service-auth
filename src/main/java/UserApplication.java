import com.mongodb.DB;
import com.mongodb.MongoClient;
import config.UserConfiguration;
import dao.UserDao;
import dao.UserDaoImpl;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import resource.UserResource;
import service.UserService;
import service.UserServiceImpl;

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
        UserService service = new UserServiceImpl(dao);
        UserResource resource = new UserResource(service);
        
        environment.jersey().register(resource);
    }

    private DB setupMongoDB(UserConfiguration configuration, Environment environment) throws UnknownHostException {
        MongoClient mongoClient = new MongoClient(configuration.getMongoHost(), configuration.getMongoPort());
        DB db = mongoClient.getDB(configuration.getMongoDb());
        MongoManaged mongoManaged = new MongoManaged(mongoClient);
        environment.lifecycle().manage(mongoManaged);
        return db;
    }
}
