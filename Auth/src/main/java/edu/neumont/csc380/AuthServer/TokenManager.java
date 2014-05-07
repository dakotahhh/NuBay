package edu.neumont.csc380.AuthServer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;

import edu.neumont.csc380.Database.AuthorizationToken;

public class TokenManager {
	private static final int MINUTES_TO_EXPIRE = 60;
	private static final int SECONDS_IN_MINUTE = 60;
	private static final int MILLIS_IN_SECOND = 1000;
	private static final String KEY_DELIMITER = "|";
	private static final int SIGNATURE_INDEX = 0;
	private static final int USER_ID_INDEX = 1;
	private static final int EXPIRY_INDEX = 2;
	
	private static final String ENCRYPTION_PASSWORD = "davidIsManly";
	
	private static StandardPBEStringEncryptor jasypt;
	private static boolean isConfigured;
	
	//TEMPORARY
	private static List<String> acceptedUUIDs = new ArrayList<String>();
	
	private static void configure(){
		 jasypt = new StandardPBEStringEncryptor();
		 jasypt.setPassword(ENCRYPTION_PASSWORD);
		 isConfigured = true;
	}
	
	public static boolean isValidTokenSignature(AuthorizationToken token){
		return acceptedUUIDs.contains(token.getSignature());
	}
	
	public static boolean isTokenExpired(AuthorizationToken token){
		return !token.getExpiry().before(new Date());
	}
	
	public static String generateEncryptedTokenString(String userId){
		if (!isConfigured){
			configure();
		}
		
		long nowMillis = new Date().getTime();
		long expiryMillis = toMillis(MINUTES_TO_EXPIRE);
		java.sql.Date expiryDate = new java.sql.Date(nowMillis + expiryMillis);
		String expiryStr = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(expiryDate);
		
		String signature = UUID.randomUUID().toString().toUpperCase();
		acceptedUUIDs.add(signature);
		String key = signature +
				KEY_DELIMITER + userId +
				KEY_DELIMITER + expiryStr;
		
		return jasypt.encrypt(key);
	}
	
	// MR. KRABS, I HAVE AN IDEA!!!
	// Who ya callin Pinhead, Pinhead?
	// F is for friends who do stuff together
	// U is for ukelele
	// N is for anywhere and any time at all down here in the deep blue sea!
	
	// F is for fire that burns down the whole town
	// U is for uranium..... bombs.
	// N is for no survivors!
	public static AuthorizationToken generateTokenOutOfThinAir_justForNorm(String userId){
		return decryptToken(generateEncryptedTokenString(userId));
	}
	
	public static AuthorizationToken decryptToken(String token){
		if (!isConfigured){
			configure();
		}
		
		String key = jasypt.decrypt(token);
		String[] keyParams = key.split("[" + KEY_DELIMITER + "]");
		
		String signature = keyParams[0];
		String userId = keyParams[1];
		String expiryStr = keyParams[2];
		
		Date expiry = null;
		
		try {
			expiry = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(expiryStr);
		} catch (ParseException e) {
			// TODO THROW MAPPED EXCEPTION!!!!!!!!!!!!
			e.printStackTrace();
		}

		AuthorizationToken authToken = new AuthorizationToken();
		authToken.setExpiry(new java.sql.Date(expiry.getTime()));
		authToken.setUserId(userId);
		authToken.setSignature(signature);
		
		return authToken;
	}
	
	private static long toMillis(int minutes){
		return minutes * SECONDS_IN_MINUTE * MILLIS_IN_SECOND;
	}
}
