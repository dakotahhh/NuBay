package edu.neumont.csc380.ExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import edu.neumont.csc380.Exceptions.InvalidPasswordOrUserNameException;
import edu.neumont.csc380.Exceptions.StatusCodes;

@Provider
@Service
public class InvalidPasswordOrUserExceptionMapper implements ExceptionMapper<InvalidPasswordOrUserNameException>
{
	public Response toResponse(InvalidPasswordOrUserNameException e) 
	{
		return Response.status(StatusCodes.INVALID_PASSWORD).entity(e.getMessage()).build();
	}
}