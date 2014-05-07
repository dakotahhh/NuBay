package edu.neumont.csc380.ExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import edu.neumont.csc380.Exceptions.NoUserFoundException;
import edu.neumont.csc380.Exceptions.StatusCodes;

@Provider
@Service
public class NoUserFoundExceptionMapper implements ExceptionMapper<NoUserFoundException>
{

	public Response toResponse(NoUserFoundException e) 
	{
		return Response.status(StatusCodes.NO_USERNAME).entity(e.getMessage()).build();
	}

}
