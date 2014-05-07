package edu.neumont.csc380.Database;

import java.util.HashMap;

public class Users {
	private HashMap<String, User> table = new HashMap<String, User>();
	private int currentIndex = 1;
	
	//-- TEST CODE --//
	public Users(){
		User norm = new User();
		norm.setUserId("1");
		norm.setUserName("norm");
		norm.setPassword("password");
		
		User nick = new User();
		nick.setUserId("2");
		nick.setUserName("nick");
		nick.setPassword("boneThugs");
		
		User david = new User();
		david.setUserId("3");
		david.setUserName("david");
		david.setPassword("twinkleTwinkle");
		
		table.put("1", norm);
		table.put("2", nick);
		table.put("3", david);
	}
	
	public User getUserById(String id) {
		return table.get(id);
	}
	
	public User getUserByName(String username) {
		User user = null;
		for(User u : table.values()) {
			if(u.getUserName().equals(username)) {
				user = u;
			}
		}
		return user;
	}
	
	public boolean containsUser(String id) {
		return table.containsKey(id);
	}
	
	public boolean containsUserName(String username) {
		boolean contains = false;
		for(User u : table.values()) {
			if(u.getUserName().equals(username)) {
				contains = true;
			}
		}
		return contains;
	}
	
	public void addUser(User user) {
		user.setUserId(currentIndex+"");
		table.put(currentIndex+"", user);
		currentIndex++;
	}
	
	public void addUser(String userName, String password) {
		User user = new User();
		user.setUserId(currentIndex+"");
		user.setUserName(userName);
		user.setPassword(password);
		table.put(currentIndex+"", user);
		currentIndex++;
	}
	
	public void deleteUser(User user) {
		table.remove(user.getUserId());
	}
	
	public void deleteUser(String id) {
		table.remove(id);
	}
}