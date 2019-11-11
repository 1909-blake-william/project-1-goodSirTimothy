package dao;

import java.sql.Connection;
import java.sql.SQLException;

import models.User;
import utils.connectionUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean findUsername(String username) {
		try (Connection conn = connectionUtil.getConnection()){
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public User findUserByUsernameAndPassword(String username, String password) {
		UserDao user = UserDao.currentUser;
		try (Connection conn = connectionUtil.getConnection()){
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
