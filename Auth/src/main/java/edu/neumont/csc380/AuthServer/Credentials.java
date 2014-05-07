package edu.neumont.csc380.AuthServer;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "credentials")
public class Credentials
{

	private int userId;
	
	private String userName;
	private String password;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return userId;
	}
	public void setId(int id) {
		this.userId = id;
	}
}
