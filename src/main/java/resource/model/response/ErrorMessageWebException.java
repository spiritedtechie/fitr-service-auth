package resource.model.response;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class ErrorMessageWebException extends WebApplicationException {

    public ErrorMessageWebException(ErrMsg errMsg) {
        this(errMsg.getValue().getValue(), errMsg.toString());
    }

    public ErrorMessageWebException(int statusCode, String msg) {
        super(Response.status(statusCode).entity(new ErrorMsgDto(msg)).build());
    }
}
