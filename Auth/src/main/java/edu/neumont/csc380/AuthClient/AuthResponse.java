package edu.neumont.csc380.AuthClient;

public class AuthResponse {
	private final int OK_STATUS = 200;
	private final String OK_START_DIGIT = "2";
	
	private int statusCode;
	private String userId;
	private String token;
	
	private String error;// for testing
	public String getError(){
		return error;
	}
	
	public AuthResponse(){
		setStatusCode(OK_STATUS);
	}
	
	public AuthResponse(int status){
		setStatusCode(status);
	}
	
	/*
	 * CURRENTLY FOR TESTING PURPOSES
	 */
	public AuthResponse(int status, String errorMessage){
		setStatusCode(status);
		error = errorMessage;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public boolean isSuccess() {
		Integer status = new Integer(statusCode);
		return status.toString().startsWith(OK_START_DIGIT);
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	@Override
	public String toString(){
		String result = "--- AUTH RESPONSE ---\n" +
						"Status: " + statusCode + "\n" +
						"UserID: " + userId + "\n" +
						"Token: " + token + "\n" +
						"Error: " + error + "\n";
		
		return result;
	}
}
