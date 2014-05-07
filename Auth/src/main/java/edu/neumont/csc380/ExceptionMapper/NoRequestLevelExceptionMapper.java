package edu.neumont.csc380.ExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import edu.neumont.csc380.Exceptions.NoRequestLevelException;
import edu.neumont.csc380.Exceptions.StatusCodes;

@Provider
@Service
public class NoRequestLevelExceptionMapper implements ExceptionMapper<NoRequestLevelException>
{
	public Response toResponse(NoRequestLevelException e) 
	{
		return Response.status(StatusCodes.NO_REQUEST_LEVEL).entity(e.getMessage()).build();
	}

}
