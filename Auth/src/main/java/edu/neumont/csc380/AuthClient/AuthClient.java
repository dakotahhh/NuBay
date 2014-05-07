package edu.neumont.csc380.AuthClient;

import java.util.Arrays;

import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.client.JAXRSClientFactory;
import org.apache.cxf.jaxrs.provider.json.JSONProvider;

import edu.neumont.csc380.AuthServer.AuthPayload;
import edu.neumont.csc380.AuthServer.Credentials;
import edu.neumont.csc380.AuthServer.IAuthenticationService;
import edu.neumont.csc380.AuthServer.IAuthorizationService;

public class AuthClient {
	private final int OK = 200;
	private final int NO_CONTENT = 204;
	
	private IAuthenticationService authenticationSvc = 
			JAXRSClientFactory.create("http://localhost:8080/authorization/rest", 
					IAuthenticationService.class, Arrays.asList(new JSONProvider<Object>())); 
	private IAuthorizationService authorizationSvc = 
			JAXRSClientFactory.create("http://localhost:8080/authorization/rest", 
					IAuthorizationService.class, Arrays.asList(new JSONProvider<Object>())); 
	
	public AuthResponse login(String userName, String password){
		Credentials creds = new Credentials();
		creds.setUserName(userName);
		creds.setPassword(password);
		
		return invokeAuthMethod(AuthInvoker.LOGIN, null, creds);
	}
	
	public AuthResponse verify(String userId, String token){
		AuthPayload payload = new AuthPayload();
		payload.setId(userId);
		payload.setToken(token);
		
		return invokeAuthMethod(AuthInvoker.VERIFY, payload, null);
	}
	
	public boolean logout(String userId){
		boolean success = false;
		AuthPayload payload = new AuthPayload();
		payload.setId(userId);
		AuthResponse response = invokeAuthMethod(AuthInvoker.LOGOUT, payload, null);
		if(response != null) {
			int status = response.getStatusCode();
			success = (status == OK || status == NO_CONTENT);
		}
		return success;
	}
	
	public AuthResponse createAuth(String userId, String userName, String password){
		Credentials creds = new Credentials();
		creds.setId(Integer.parseInt(userId));
		creds.setUserName(userName);
		creds.setPassword(password);
		
		return invokeAuthMethod(AuthInvoker.CREATE_AUTH, null, creds);
	}
	
	public AuthResponse checkAuth(String userId, String token){
		AuthPayload payload = new AuthPayload();
		payload.setId(userId);
		payload.setToken(token);
		
		return invokeAuthMethod(AuthInvoker.CHECK_AUTH, payload, null);
	}
	
	public AuthResponse updateAuth(String userId, String token, String userName, String password){
		AuthPayload payload = new AuthPayload();
		payload.setId(userId);
		payload.setToken(token);
		payload.setUserName(userName);
		payload.setPassword(password);
		
		return invokeAuthMethod(AuthInvoker.UPDATE_AUTH, payload, null);
	}
	
	public boolean deleteAuth(String userId){
		AuthPayload payload = new AuthPayload();
		payload.setId(userId);
		
		AuthResponse response = invokeAuthMethod(AuthInvoker.DELETE_AUTH, payload, null);
		int status = response.getStatusCode();
		
		return (status == OK || status == NO_CONTENT);
	}
	
	private AuthResponse invokeAuthMethod(AuthInvoker auth, AuthPayload payload, Credentials creds){
		AuthResponse result = null;
		try {
			Response response = auth.invoke(authenticationSvc, authorizationSvc, payload, creds);
			if (response != null){
				result = new AuthResponse(response.getStatus());
				if(auth != AuthInvoker.LOGOUT) {
					AuthPayload authLoad = response.readEntity(AuthPayload.class);
					result.setUserId(authLoad.getId());
					result.setToken(authLoad.getToken());
				}
			} 
		} catch (Exception e) {
			result = new AuthResponse(400, e.toString() + " -- " + e.getCause() + " -- " + e.getMessage() + " -- " + e.getLocalizedMessage());
		}
		return result;
	}
}
