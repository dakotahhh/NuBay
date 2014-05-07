package edu.neumont.csc380.AuthServer;

import java.util.Date;

import javax.ws.rs.core.Response;

import org.springframework.stereotype.Service;

import edu.neumont.csc380.Database.AuthorizationToken;
import edu.neumont.csc380.Database.DataBase;
import edu.neumont.csc380.Database.User;
import edu.neumont.csc380.Exceptions.InvalidPasswordOrUserNameException;
import edu.neumont.csc380.Exceptions.NoIDException;
import edu.neumont.csc380.Exceptions.NoUserFoundException;
import edu.neumont.csc380.Exceptions.TokenExpiredException;
import edu.neumont.csc380.Exceptions.TokenInvalidException;
import edu.neumont.csc380.Exceptions.TokenMissingException;

@Service("IAuthenticationService")
public class AuthenticationService implements IAuthenticationService {
	
	public Response handleLoginRequest(Credentials credentials) throws Exception {		
		Response response = null;
		User user = DataBase.getUsers().getUserByName(credentials.getUserName());
		
		boolean userExists = user != null;
		if(userExists) {
			if(user.getPassword().equals(credentials.getPassword())) {
				String token = TokenManager.generateEncryptedTokenString(user.getUserId());
				DataBase.getTokens().addToken(TokenManager.decryptToken(token));
				AuthPayload payload = new AuthPayload();
				payload.setId(user.getUserId());
				payload.setToken(token);
				
				response = Response.status(200).entity(payload).build();
			}
			else { throw new InvalidPasswordOrUserNameException("Incorrect username or password match."); }
		}
		else { throw new InvalidPasswordOrUserNameException("Incorrect username or password match."); }
		return response;
	}

	public Response handleLogoutRequest(AuthPayload payload) throws Exception {
		String id = payload.getId();
		verifyIdExists(id);
		verifyUserExists(payload);
		if (DataBase.getTokens().getTokenByUserId(id) != null); 
			DataBase.getTokens().deleteToken(id);
		return Response.status(204).build();
	}

	//TODO: This needs to create a user not check if one exists
	public Response handleCreationRequest(Credentials creds) throws Exception {
		Response response = null;
		boolean exists = DataBase.getUsers().containsUser(creds.getUserName()); //CHECK IF USER EXISTS
		if (!!!exists) {
			DataBase.getUsers().addUser(creds.getUserName(), creds.getPassword());
			User user = DataBase.getUsers().getUserByName(creds.getUserName());
			String tokenString = TokenManager.generateEncryptedTokenString(user.getUserId());
			AuthorizationToken token = TokenManager.decryptToken(tokenString);
			DataBase.getTokens().addToken(token);
			
			AuthPayload payload = new AuthPayload();
			payload.setId(new Integer(creds.getId()).toString());
			payload.setToken(tokenString);
			response = Response.status(200).entity(payload).build();
		}
		else { throw new NoUserFoundException("Username not found"); }
		return response;
	}

	/* (non-Javadoc)
	 * @see edu.neumont.csc380.AuthServer.IAuthenticationService#verify(edu.neumont.csc380.AuthServer.AuthPayload)
	 */
	public Response verify(AuthPayload auth) throws Exception {
		Response response = null;
		if(auth.getToken() != null && !auth.getToken().isEmpty()) {
			AuthorizationToken userToken = TokenManager.decryptToken(auth.getToken());
			String userID = userToken.getUserId();
			verifyUserExists(auth);
			validateUserToken(userID, userToken);
			response = generateResponse(userID);
		} else { throw new TokenMissingException(); }
		return response;
	}

	/**
	 * Generates a response after validating the token
	 * The response will have a AuthPayload with only the id
	 * 
	 * @param userID
	 * @return response with an AuthPayload
	 */
	private Response generateResponse(String userID) {
		Response response;
		AuthorizationToken token = DataBase.getTokens().getTokenByUserId(userID);
		AuthPayload payload = new AuthPayload();
		payload.setId(token.getUserId());
		response = Response.status(200).entity(payload).build();
		return response;
	}

	/**
	 * Generates and updates a token in the database
	 * @param userID
	 * @return
	 */
	private String updateToken(String userID) {
		String tokenString = TokenManager.generateEncryptedTokenString(userID);
		AuthorizationToken token = TokenManager.decryptToken(tokenString);
		DataBase.getTokens().updateToken(token);
		return tokenString;
	}

	/**
	 * Validates the token provided is valid
	 * Checks the userID, Signature, and Expiry of a token match
	 * @param userID
	 * @param userToken
	 * @throws TokenInvalidException
	 * @throws TokenExpiredException
	 * @throws TokenMissingException
	 */
	private AuthorizationToken validateUserToken(String userID, AuthorizationToken userToken) throws Exception {
		AuthorizationToken dbToken =  DataBase.getTokens().getTokenByUserId(userID);
		if(dbToken == null) {
			throw new TokenMissingException();
		}
		if ( !dbToken.getExpiry().equals(userToken.getExpiry()) ||
			 !dbToken.getSignature().equals(userToken.getSignature()) || 
			 !dbToken.getUserId().equals(userToken.getUserId()) ) {
			
			throw new TokenInvalidException("The token provided is not valid");
		}
		if ( userToken.getExpiry().before(new Date()) ) {
			throw new TokenExpiredException("The token provided has expired");
		}
		return dbToken;
	}

	/**
	 * Will verify the user exists in the database
	 * @param auth
	 * @throws NoUserFoundException
	 */
	private void verifyUserExists(AuthPayload auth) throws NoUserFoundException {
		boolean exists = DataBase.getUsers().containsUser(auth.getId()); //CHECK IF USER EXISTS
		if (!exists) {
			throw new NoUserFoundException("Username not found");
		}
	}

	/**
	 * Checks the user is not null or empty
	 * 
	 * @param id
	 * @throws NoIDException
	 */
	private void verifyIdExists(String id) throws NoIDException {
		if (id == null || id.isEmpty()){
			throw new NoIDException("No ID was provided in the request");
		}
	}
}