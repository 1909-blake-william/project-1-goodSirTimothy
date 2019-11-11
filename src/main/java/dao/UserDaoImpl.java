package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import models.User;
import utils.connectionUtil;

public class UserDaoImpl implements UserDao{
	User user;

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
		try (Connection conn = connectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_users u "
					+ "LEFT JOIN ers_user_roles r ON (u.user_role_id = r.ers_user_role_id) "
					+ "WHERE ers_username = ? AND ers_password = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			if(rs.next()) {
				int userId = rs.getInt("ers_users_id");
				String firstName = rs.getString("user_first_name");
				String lastName = rs.getString("user_last_name");
				String email = rs.getString("user_email");
				String userRole = rs.getString("user_role");
				user = new User(userId, username, password, firstName, lastName, email, userRole);
				return user;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public User currentUser() {
		return user;
	}

}
