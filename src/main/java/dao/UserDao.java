package dao;

import models.User;

public interface UserDao {
	UserDao currentUser = new UserDaoImpl();
	/**
	 * check if the user exists.
	 * @param username = the name entered
	 * @return return true or false if the information is correct or not
	 */
	boolean findUsername(String username);
	
	/**
	 * find the username by username and password
	 * @param username = the username inputed
	 * @param password = the password inputed
	 * @return return a User object if the user is found. Else, return null.
	 */
	User findUserByUsernameAndPassword(String username, String password);
	
	
}
