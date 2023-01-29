package controller;

import dataaccess.UserDA;
import model.User;

/*
 * controller class that link between data access 
 * class on Doctors and the view of Student frames for the user
 * used for authentications
 */
/**
 * @author Mahmoud
 *
 */
public class UserController {
	// instance of student date access class that deal with database
	private UserDA dataAccess;

	public UserController() {
		this.dataAccess = new UserDA();
	}

	// authenticate user
	public String signIn(String userName, String password) {
		User user = this.dataAccess.getUserByUserName(userName);
		if (user != null) {
			if (user.getPassword().equals(password))
				return "ok";
			return ("wrong password!. You are not " + userName);
		}
		return "userName " + userName + " is not found!";

	}

	// get User'doctor' department using its userName
	public String getDepartment(String userName) {
		return this.dataAccess.getUserByUserName(userName).getDepartment().toLowerCase();
	}

	// register new user'doctor'
	public String signUp(String userName, String password, String department) {
		if (userName == null || password == null || department == null || userName.equals("") || password.equals("")
				|| department.equals(""))
			return "please enter all fields";
		if (!department.toLowerCase().equals("it") && !department.toLowerCase().equals("is")
				&& !department.toLowerCase().equals("cs") && !department.toLowerCase().equals("ds"))
			return "department should be one of [CS, IT, IS, DS]";
		User user = new User(userName, password, department);
		if (this.dataAccess.addUser(user))
			return "ok";
		return "this userName already used";
	}

}
