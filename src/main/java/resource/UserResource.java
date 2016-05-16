package resource;

import com.codahale.metrics.annotation.Timed;
import dao.UserDao;
import model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserDao dao;

    public UserResource(UserDao dao) {
        this.dao = dao;
    }

    @GET
    @Timed
    public User get(@QueryParam("email_address") String emailAddress) {
        return null;
    }

    @POST
    @Timed
    public User create(User user) {
        return dao.create(user);
    }

    @PUT
    @Timed
    public User updateUser(User user) {
        return null;
    }

}
