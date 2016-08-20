package resource;

import model.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import resource.model.CredentialsDto;
import resource.model.ErrMsg;
import resource.model.ErrorMessageWebException;
import resource.model.SessionDto;
import service.AuthenticateService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/")
public class AuthenticateResource {

    private final static Logger LOG = LoggerFactory.getLogger(AuthenticateResource.class);

    private AuthenticateService authenticateService;

    public AuthenticateResource(AuthenticateService authenticateService) {
        this.authenticateService = authenticateService;
    }

    @Path("/authenticate")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response authenticate(CredentialsDto credentialsDto) {
        try {
            Session session = authenticateService.authenticate(credentialsDto.getEmail(), credentialsDto.getPassword());
            return Response.ok().entity(SessionDto.from(session)).build();
        } catch (Exception e) {
            LOG.error("Auth resource failed", e);
            throw new ErrorMessageWebException(ErrMsg.AUTH_FAILED);
        }
    }

    @Path("/validate-token")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.TEXT_PLAIN)
    public Response validate(String token) {
        try {
            authenticateService.authenticateWithToken(token);
            return Response.ok().build();
        } catch (Exception e) {
            LOG.error("Auth resource failed", e);
            throw new ErrorMessageWebException(ErrMsg.AUTH_FAILED);
        }
    }
}

