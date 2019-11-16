package dao;

import models.User;

/**
 * An interface for all User methods that handle SQL queries
 * @author Tim Clifton
 *
 */
public interface UserDao {
	
	/**
	 * The current user that has logged into the Database
	 */
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
	User findByUsernameAndPassword(String username, String password);
	
	/**
	 * return the current user
	 * @return User
	 */
	User currentUser();
	
}
