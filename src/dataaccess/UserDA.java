package dataaccess;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.User;

/*
 * data access class for dealing with stored data on database
 * for classes provide users data
 */
/**
 * @author Mahmoud
 *
 */

public class UserDA {
	private String dbName = "Uni_Stuff";/// database name
	private ResultSet result;
	private Statement stat;
	private DatabaseConnector con;

	public UserDA() {
		this.con = new DatabaseConnector(dbName);
	}

	public boolean addUser(User user) {
		// sure it not duplicated
		if (this.isFound(user.getUsername())) {
			return false;
		}
		// query to be executed
		String query = "INSERT INTO Users(userName,password,department) VALUES('" + user.getUsername().toLowerCase()
				+ "','" + user.getPassword().toLowerCase() + "','" + user.getDepartment().toLowerCase() + "')";
		try {
			// create statement and execute
			stat = this.con.connect().createStatement();
			stat.execute(query);
			this.con.disConnect();
			System.out.println("user added");
			return true;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// free the resource
		}
		this.con.disConnect();
		return false;
	}

	public User getUserByUserName(String userName) {
		userName = userName.toLowerCase();
		// query to be executed
		String query = "SELECT * FROM Users u WHERE u.UserName='" + userName + "'";
		try {
			// create statement and execute
			stat = this.con.connect().createStatement();
			result = stat.executeQuery(query);
			if (result.next()) {
				// user is found
				String uname = result.getString(1);
				String upass = result.getString(2);
				String udep = result.getString(3);
				// close connection
				con.disConnect();
				return (new User(uname, upass, udep));
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			// free the resource
		}
		this.con.disConnect();
		return null;
	}

	// to check if user found or not
	public boolean isFound(String userName) {
		if (this.getUserByUserName(userName) == null) {
			return false;
		}
		return true;
	}

	// get all user'doctors' for a department
	public List<User> getUsersByDepartment(String department) {
		// create statement and execute
		String query = "SELECT * FROM Users WHERE Department = '" + department.toLowerCase() + "'";
		try {
			// create statement and execute
			stat = con.connect().createStatement();
			result = stat.executeQuery(query);
			// store the result on a list
			List<User> match = new ArrayList<>();
			while (result.next()) {
				User user = new User();
				user.setUsername(result.getString("UserName"));
				user.setPassword(result.getString("Password"));
				user.setDepartment(result.getString("Department"));
				match.add(user);
			}
			this.con.disConnect();
			return match;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		con.disConnect();
		return null;
	}

	// unregister user
	public boolean deleteUser(String username) {
		// query to be executed
		String query = "DELETE FROM Users WHERE userName='" + username + "'";
		try {
			// create statement and execute
			stat = this.con.connect().createStatement();
			stat.execute(query);
			con.disConnect();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
		con.disConnect();
		return false;
	}

}
