package edu.neumont.csc380.ExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import edu.neumont.csc380.Exceptions.StatusCodes;
import edu.neumont.csc380.Exceptions.TokenMissingException;

@Provider
@Service
public class TokenMissingExceptionMapper implements ExceptionMapper<TokenMissingException>
{
	public Response toResponse(TokenMissingException e) 
	{
		return Response.status(StatusCodes.TOKEN_MISSING).entity(e.getMessage()).build();
	}
}