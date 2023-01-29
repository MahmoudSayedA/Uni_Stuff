package model;

public class User {
	private String password;
	private String department;

	public User(String username, String password, String department) {
		super();
		Username = username;
		this.password = password;
		this.department = department;
	}

	public User() {

	};

	private String Username;

	public String getUsername() {
		return Username;
	}

	public void setUsername(String username) {
		Username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	@Override
	public String toString() {
		return "User [Username=" + Username + ", password=" + password + ", department=" + department + "]";
	}
}
