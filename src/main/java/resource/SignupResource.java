package resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resource.model.ErrMsg;
import resource.model.ErrorMessageWebException;
import resource.model.CredentialsDto;
import service.SignupService;

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
    public Response signup(CredentialsDto credentialsDto) {
        try {
            signupService.signup(credentialsDto.getEmail(), credentialsDto.getPassword());
        } catch (Exception e) {
            LOG.error("Signup resource failed", e);
            throw new ErrorMessageWebException(ErrMsg.SIGNUP_FAILED);
        }

        return Response.ok().build();
    }
}
