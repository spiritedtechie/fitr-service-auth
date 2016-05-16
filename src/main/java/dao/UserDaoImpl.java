package dao;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import model.User;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

public class UserDaoImpl implements UserDao {

    private final DB database;

    public UserDaoImpl(DB db) {
        this.database = db;
    }

    public User create(User user) {

        JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(
                database.getCollection("users"), User.class, String.class);


        WriteResult<User, String> result = coll.insert(user);

        return result.getSavedObject();
    }

    public User getUserByEmail(String emailAddress) {

        JacksonDBCollection<User, String> coll = JacksonDBCollection.wrap(
                database.getCollection("users"), User.class, String.class);

        BasicDBObject whereQuery = new BasicDBObject();
        whereQuery.put("emailAddress", emailAddress);

        return coll.findOne(whereQuery);
    }
}
