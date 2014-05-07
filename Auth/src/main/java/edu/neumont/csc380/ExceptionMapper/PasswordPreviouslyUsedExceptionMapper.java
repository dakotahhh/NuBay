package edu.neumont.csc380.ExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import edu.neumont.csc380.Exceptions.PasswordPreviouslyUsedException;
import edu.neumont.csc380.Exceptions.StatusCodes;

@Provider
@Service
public class PasswordPreviouslyUsedExceptionMapper implements ExceptionMapper<PasswordPreviouslyUsedException>
{
	public Response toResponse(PasswordPreviouslyUsedException e) 
	{
		return Response.status(StatusCodes.PASSWORD_PREVIOUSLY_USED).entity(e.getMessage()).build();
	}
}