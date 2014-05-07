package edu.neumont.csc380.AuthClient;

import java.util.Scanner;

public class TestClient {
	private static final String QUIT_OPTION = "6";
	
	static Scanner scan = new Scanner(System.in);
	AuthClient client = new AuthClient();
	
	static String token = "";
	
	public static void main(String[] args){
		System.out.println("--- WHAT HAPPENS WHEN YOU GO 'RYAN MILLER' ON A PROJECT ---");
		String input = "";
		TestClient test = new TestClient();
		
		do{
			System.out.print("1. Login\n" +
					"2. Logout\n" +
					"3. Verify Authentication\n" +
					"4. Create Authentication\n" +
					"5. Update Authentication\n" +
					"6. Quit\n" +
					"Enter option: ");
			input = scan.nextLine();
			
			try{
				int option = Integer.parseInt(input);
				
				switch(option){
				case 1:
					test.login();
					break;
				case 2:
					test.logout();
					break;
				case 3:
					test.verify();
					break;
				case 4:
					test.createAuth();
					break;
				case 5:
					test.updateAuth();
					break;
				case 6:
					System.out.println("So long, now! :D");
					break;
				default:
					System.out.println("Not a valid option. Please retry.");
					break;
				}
			}catch(Exception ex){
				System.out.println("Must enter a number!");
				ex.printStackTrace();
			}
			
		}while(!input.equals(QUIT_OPTION));
	}
	
	private void verify(){
		System.out.print("Enter userId: ");
		String userId = scan.nextLine();
		AuthResponse response = client.verify(userId, token);
		if(response == null || response.isSuccess()) {
			System.out.println("Verification succeeded.\n");
		} else { System.out.println("Verification resulted to be invalid!\n"); }
	}
	
	private void login(){
		System.out.print("Enter username: ");
		String username = scan.nextLine();
		System.out.print("Enter password: ");
		String password = scan.nextLine();
		
		AuthResponse response = client.login(username, password);
		System.out.println(response.toString());
		
		if( response.isSuccess() ) {
			promptStoreToken(response.getToken());
		} else {
			System.out.println("Login failed!\n"); 
		}
	}
	
	private void logout(){
		System.out.print("Enter userId: ");
		String userId = scan.nextLine();
		
		boolean success = client.logout(userId);
		if(success) {
			System.out.println("Successfully logged out.");
		} else {
			System.out.println("Log out failed.");
		}
	}
	
	private void createAuth(){
		System.out.print("Enter userId: ");
		String userId = scan.nextLine();
		System.out.print("Enter username: ");
		String userName = scan.nextLine();
		System.out.print("Enter password: ");
		String password = scan.nextLine();
		
		AuthResponse response = client.createAuth(userId, userName, password);
		System.out.println(response.toString());
		
		if( response.isSuccess() ) {
			promptStoreToken(response.getToken());
		} else {
			System.out.println("Creation failed!");
		}
	}
	
//	private void checkAuth(){
//		System.out.print("Enter userId: ");
//		String userId = scan.nextLine();
//		AuthResponse response = client.checkAuth(userId, token);
//		System.out.println(response.toString());
//	}
	
	private void updateAuth(){
		System.out.print("Enter userId: ");
		String userId = scan.nextLine();
		System.out.print("Enter username: ");
		String userName = scan.nextLine();
		System.out.print("Enter new password: ");
		String password = scan.nextLine();
		AuthResponse response = client.updateAuth(userId, token, userName, password);
		System.out.println(response.toString());

		if( response.isSuccess() ) {
			promptStoreToken(response.getToken());
		}
		else { 
			System.out.println("Update failed!\n"); 
		}
	}
	
//	private void deleteAuth(){
//		System.out.print("Enter userId: ");
//		String userId = scan.nextLine();
//		boolean success = client.deleteAuth(userId);
//		System.out.println("Succeeded: " + success);
//	}
	
	private void promptStoreToken(String tokenStr){
		System.out.print("Would you like to store the token? y/n: ");
		String input = scan.nextLine().toLowerCase();
		
		if (input.equals("y")){
			token = tokenStr;
			System.out.println("Token successfully stored.\n");
		}
	}
}
