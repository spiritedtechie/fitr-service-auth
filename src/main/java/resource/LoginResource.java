package resource;

import model.AuthTokenRole;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resource.model.request.UserDto;
import resource.model.response.ErrMsg;
import resource.model.response.ErrorMessageWebException;
import service.LoginService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/login")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class LoginResource {

    private final static Logger LOG = LoggerFactory.getLogger(LoginResource.class);

    private LoginService loginService;

    public LoginResource(LoginService loginService) {
        this.loginService = loginService;
    }

    @POST
    public Response login(UserDto userDto) {
        try {
            AuthTokenRole authTokenRole = loginService.login(userDto.getEmail(), userDto.getPassword());
            return Response.ok().entity(AuthTokenRole.from(authTokenRole)).build();
        } catch (Exception e) {
            LOG.error("Login resource failed", e);
            throw new ErrorMessageWebException(ErrMsg.LOGIN_FAILED);
        }
    }
}

