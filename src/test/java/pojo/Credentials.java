package pojo;

public class Credentials {
	
	//create a Java class equivalent to body
	
	private String username;
	private String password;
	
	
	//create a constructor --> Source --Generate Constructor
	public Credentials(String username, String password) {
		this.username = username;
		this.password = password;
	}

	//getter and setter  --> Source --Generate getter and setter
	public String getUsername() {
		return username;
	}


	public void setUsername(String username) {
		this.username = username;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	

}
