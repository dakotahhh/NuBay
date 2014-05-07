package edu.neumont.csc380.ExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import edu.neumont.csc380.Exceptions.StatusCodes;
import edu.neumont.csc380.Exceptions.TokenInvalidException;

@Provider
@Service
public class TokenInvalidExceptionMapper implements ExceptionMapper<TokenInvalidException>
{
	public Response toResponse(TokenInvalidException e) 
	{
		return Response.status(StatusCodes.TOKEN_INVALID).entity(e.getMessage()).build();
	}
}