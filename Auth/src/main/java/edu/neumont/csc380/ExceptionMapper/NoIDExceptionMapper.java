package edu.neumont.csc380.ExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import edu.neumont.csc380.Exceptions.NoIDException;
import edu.neumont.csc380.Exceptions.StatusCodes;

@Provider
@Service
public class NoIDExceptionMapper implements ExceptionMapper<NoIDException>
{

	public Response toResponse(NoIDException e) 
	{
		return Response.status(StatusCodes.NO_ID).entity(e.getMessage()).build();
	}

}
