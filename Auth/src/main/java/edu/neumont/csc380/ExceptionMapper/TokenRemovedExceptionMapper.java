package edu.neumont.csc380.ExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import edu.neumont.csc380.Exceptions.StatusCodes;
import edu.neumont.csc380.Exceptions.TokenRemovedException;

@Provider
@Service
public class TokenRemovedExceptionMapper implements ExceptionMapper<TokenRemovedException>
{
	public Response toResponse(TokenRemovedException e) 
	{
		return Response.status(StatusCodes.TOKEN_REMOVED).entity(e.getMessage()).build();
	}
}