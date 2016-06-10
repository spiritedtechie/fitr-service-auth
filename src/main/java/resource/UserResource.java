package resource;

import com.codahale.metrics.annotation.Timed;
import dao.UserDao;
import model.User;
import service.UserService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

    private UserService service;

    public UserResource(UserService service) {
        this.service = service;
    }

    @GET
    @Timed
    public User get(@QueryParam("email_address") String emailAddress) {
        return service.get(emailAddress);
    }

    @POST
    @Timed
    public User create(User user) {
        return service.create(user);
    }

    @PUT
    @Timed
    public User updateUser(User user) {
        return null;
    }

}
