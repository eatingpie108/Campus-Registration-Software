package classRegistration;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	// These are the required data fields for each user in the system
	private String username = null;
	private String password = null;
	private String firstname = null;
	private String lastname = null;
	
	User(){} // This is the default no args constructor
	
	User(String u, String p, String f, String l){ // This is the Constructor that can set the various data fields 
		this.username = u;
		this.password = p;
		this.firstname = f;
		this.lastname = l;
	}
	
	//This next block of code is just setting up Getters and Setters for the data fields 
	public void setUsername(String u) {
		this.username = u;
	}
	public String getUsername() {
		return this.username;
		}
	public void setPassword(String p) {
		this.password = p;
	}
	public String getPassword() {
		return this.password;
	}
	public void setFirstname(String f) {
		this.firstname = f;
	}
	public String getFirstname() {
		return this.firstname;
	}
	public void setLastname(String l) {
		this.lastname = l;
	}
	public String getLastname() {
		return this.lastname;
	}

	public String getFullName(){	
		return (this.firstname + " " + this.lastname);
	}
	@Override
	public String toString() {
		return this.username + " " + this.password;
	}
}
