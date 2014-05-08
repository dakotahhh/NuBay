package edu.neumont.csc380.AuthServer;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import client.client.AuthPayload;
import edu.neumont.csc380.Database.AuthorizationToken;
import edu.neumont.csc380.Database.DataBase;
import edu.neumont.csc380.Database.User;
import edu.neumont.csc380.Exceptions.TokenExpiredException;
import edu.neumont.csc380.Exceptions.TokenInvalidException;
import edu.neumont.csc380.Exceptions.TokenMissingException;

@Service("IAuthorizationService")
public class AuthorizationService implements IAuthorizationService {
	public static String signature = "authServer2727LolzYoloSwag";
	public static long expirationTime = 120000;
	
	public Response handleAuthorizationRequest(AuthPayload payload) throws Exception {
		AuthorizationHandler handler = new AuthorizationHandler() {	
			public Response handlerAuthorizationRequest(AuthPayload payload) {
//				String newToken = TokenManager.generateEncryptedTokenString(payload.getId());
//				AuthPayload authLoad = new AuthPayload();
//				authLoad.setId(payload.getId());
//				authLoad.setToken(newToken);
				
				return Response.status(200).entity(payload).build();
			}
		};
		return processAuthPayload(payload, handler);
	}

	/**
	 * Allows for an authorized user to update its password.
	 */
	public Response handleUpdateRequest(AuthPayload payload) throws Exception {
		AuthorizationHandler handler = new AuthorizationHandler() {	
			public Response handlerAuthorizationRequest(AuthPayload payload) {
				User user = DataBase.getUsers().getUserById(payload.getId());
				user.setPassword(payload.getPassword());
				
				String encryptedToken = TokenManager.generateEncryptedTokenString(user.getUserId());
				AuthorizationToken token = TokenManager.decryptToken(encryptedToken);
				DataBase.getTokens().updateToken(token);
				
				AuthPayload newLoad = new AuthPayload();
				newLoad.setId(user.getUserId());
				newLoad.setToken(encryptedToken);
				return Response.status(200).entity(newLoad).build();
			}
		};
		return processAuthPayload(payload, handler);
	}

	public Response handleDeletionRequest(AuthPayload payload) throws Exception {
		AuthorizationHandler handler = new AuthorizationHandler() {	
			public Response handlerAuthorizationRequest(AuthPayload payload) {
				DataBase.getUsers().deleteUser(payload.getId());
				return Response.accepted().build();
			}
		};
		return processAuthPayload(payload, handler);
	}
	
	private Response processAuthPayload(AuthPayload payload, AuthorizationHandler handler) throws Exception {
		Response r = null;
		if(payload.getToken() != null && !payload.getToken().isEmpty()) {
			AuthorizationToken token = TokenManager.decryptToken(payload.getToken());
			if( TokenManager.isTokenExpired(token)) {
				if (TokenManager.isValidTokenSignature(token)){
					r = handler.handlerAuthorizationRequest(payload);
				} 
				else { throw new TokenInvalidException(); }	
			} 
			else { throw new TokenExpiredException(); }
		} 
		else { throw new TokenMissingException(); }
		return r;
	}
}