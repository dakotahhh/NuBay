package edu.neumont.csc380.Database;


public class DataBase
{
	private static Users users = new Users();
	private static AuthorizationTokens tokens = new AuthorizationTokens();
	
	public static Users getUsers() {
		return users;
	}
	
	public static AuthorizationTokens getTokens() {
		return tokens;
	}
}
