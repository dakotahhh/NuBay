package edu.neumont.csc380.ExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import edu.neumont.csc380.Exceptions.NoPasswordException;
import edu.neumont.csc380.Exceptions.StatusCodes;

@Provider
@Service
public class NoPasswordExceptionMapper implements ExceptionMapper<NoPasswordException>
{
	public Response toResponse(NoPasswordException e) 
	{
		return Response.status(StatusCodes.NO_PASSWORD).entity(e.getMessage()).build();
	}

}
