package edu.neumont.csc380.Database;

import java.util.Date;
import java.util.HashMap;

public class AuthorizationTokens {
	private HashMap<String, AuthorizationToken> table = new HashMap<String, AuthorizationToken>();
	
	public AuthorizationToken getTokenByUserId(String id) {
		return table.get(id);
	}
	
	public boolean containsToken(String userId) {
		return table.containsKey(userId);
	}
	
	public void addToken(AuthorizationToken token) {
		table.put(token.getUserId(), token);
	}
	
	public void updateToken( AuthorizationToken token ) {
		table.put(token.getUserId(), token);
	}
	
	public void addToken(String userId, String signature, Date expiry) {
		AuthorizationToken token = new AuthorizationToken();
		token.setUserId(userId);
		token.setSignature(signature);
		token.setExpiry(expiry);
		table.put(userId, token);
	}
	
	public void deleteToken(AuthorizationToken token) {
		table.remove(token.getUserId());
	}
	
	public void deleteToken(String id) {
		table.remove(id);
	}
}
