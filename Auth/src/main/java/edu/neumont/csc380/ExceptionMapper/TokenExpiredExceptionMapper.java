package edu.neumont.csc380.ExceptionMapper;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.springframework.stereotype.Service;

import edu.neumont.csc380.Exceptions.StatusCodes;
import edu.neumont.csc380.Exceptions.TokenExpiredException;

@Provider
@Service
public class TokenExpiredExceptionMapper implements ExceptionMapper<TokenExpiredException>
{
	public Response toResponse(TokenExpiredException e) 
	{
		return Response.status(StatusCodes.TOKEN_EXPIRED).entity(e.getMessage()).build();
	}
}