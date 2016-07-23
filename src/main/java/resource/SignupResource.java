package resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resource.model.response.ErrMsg;
import resource.model.response.ErrorMessageWebException;
import resource.model.request.UserDto;
import service.LoginServiceImpl;
import service.SignupService;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/signup")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class SignupResource {

    private final static Logger LOG = LoggerFactory.getLogger(SignupResource.class);

    private SignupService signupService;

    public SignupResource(SignupService signupService) {
        this.signupService = signupService;
    }

    @POST
    public Response signup(UserDto userDto) {
        try {
            signupService.signup(userDto.getEmail(), userDto.getPassword());
        } catch (Exception e) {
            LOG.error("Signup resource failed", e);
            throw new ErrorMessageWebException(ErrMsg.SIGNUP_FAILED);
        }

        return Response.ok().build();
    }
}
