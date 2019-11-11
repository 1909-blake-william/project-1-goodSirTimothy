package dao;

import models.User;

public interface UserDao {
	/**
	 * check if the user exists.
	 * @param username
	 * @return
	 */
	boolean findUser(String username);
	
	/**
	 * find the username by username and password
	 * @param username
	 * @param password
	 * @return
	 */
	User findUserByNameAndPassword(String username, String password);
	
	
}
