package edu.neumont.csc380.AuthServer;

import javax.ws.rs.core.Response;

public interface AuthorizationHandler {
	public Response handlerAuthorizationRequest(AuthPayload payload);
}
