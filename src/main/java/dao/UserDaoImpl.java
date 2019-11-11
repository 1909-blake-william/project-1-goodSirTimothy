package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;
import utils.connectionUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public boolean findUsername(String username) {
		try (Connection conn = connectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_users WHERE ers_username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				return true;
			}
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
			String sql = "SELECT * FROM ers_users WHERE ers_username = ? AND ers_password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(1, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
