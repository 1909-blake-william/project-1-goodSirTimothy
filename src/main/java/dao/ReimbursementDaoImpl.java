package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Reimbursement;
import utils.connectionUtil;

/**
 * This class is for submitting and selecting reimbursements from an SQL database
 * @author Tim Clifton
 * @implNote This implements the ReimbursementDao and places all needed logic in the methods
 */
public class ReimbursementDaoImpl implements ReimbursementDao {
	HashMap<Integer, String> mapOfNames = new HashMap<>();

	/**
	 * extract values for Database row and create a Reimbursement object with the values
	 * @param rs the ResultSet
	 * @return the Reimbursement Object
	 * @throws SQLException This method throws because where this method is called, it already catches the SQLException
	 */
	private Reimbursement extractTable(ResultSet rs) throws SQLException {
		int id = rs.getInt("reimb_id");
		int amount = rs.getInt("reimb_amount");
		Timestamp submitted = rs.getTimestamp("reimb_submitted");
		Timestamp resolved = rs.getTimestamp("reimb_resolved");
		String description = rs.getString("reimb_description");
		int author = rs.getInt("reimb_author");
		int resolver = rs.getInt("reimb_resolver");
		int statusId = rs.getInt("reimb_status_id");
		int typeId = rs.getInt("reimb_type_id");
		return new Reimbursement(id, amount, submitted, resolved, description, author, resolver, statusId, typeId);
	}

	/**
	 * pull the full name from the database based off the userId from Reimbursements
	 * @param r = a single Reimbursement
	 * @param author
	 * @return boolean if the name was found
	 */
	private boolean pullFullName(Reimbursement r, boolean author) {
		if (author) {
			if (mapOfNames.containsKey(r.getAuthor())) {
				r.setAutherName(mapOfNames.get(r.getAuthor()));
				return true;
			}
		} else {
			if (mapOfNames.containsKey(r.getResolver())) {
				r.setResolverName(mapOfNames.get(r.getResolver()));
				return true;
			}
		}
		try (Connection conn = connectionUtil.getConnection()) {
			String sql = "SELECT user_first_name, user_last_name FROM ers_users WHERE ers_users_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			if (author) {
				ps.setInt(1, r.getAuthor());
			} else {
				ps.setInt(1, r.getResolver());
			}

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				if (author) {
					r.setAutherName(rs.getString("user_first_name") + "," + rs.getString("user_last_name"));
					mapOfNames.put(r.getAuthor(), rs.getString("user_first_name") + "," + rs.getString("user_last_name"));
				} else {
					r.setResolverName(rs.getString("user_first_name") + "," + rs.getString("user_last_name"));
					mapOfNames.put(r.getResolver(), rs.getString("user_first_name") + "," + rs.getString("user_last_name"));
				}
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Reimbursement> getReimbursementsById(int userId) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try (Connection conn = connectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursment WHERE REIMB_AUTHOR = ? ORDER BY reimb_id desc";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reimbursements.add(extractTable(rs));
			}
			for (Reimbursement r : reimbursements) {
				pullFullName(r, true);
				pullFullName(r, false);
			}
			return reimbursements;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean postReimbursementToDataBase(int amount, String description, int author, int statusId, int typeId) {
		try (Connection conn = connectionUtil.getConnection()) {
			String sql = "INSERT INTO ers_reimbursment(REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, "
					+ "REIMB_RESOLVED, REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_RESOLVER, REIMB_STATUS_ID, REIMB_TYPE_ID) "
					+ "VALUES (REIMB_ID_SEQ.nextval, ?, CURRENT_TIMESTAMP, null, ?, ?, null, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, amount);
			ps.setString(2, description);
			ps.setInt(3, author);
			ps.setInt(4, statusId);
			ps.setInt(5, typeId);

			int result = ps.executeUpdate();

			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Reimbursement> adminGetReimbursements() {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try (Connection conn = connectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursment ORDER BY reimb_id desc";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reimbursements.add(extractTable(rs));
			}
			for (Reimbursement r : reimbursements) {
				pullFullName(r, true);
				pullFullName(r, false);
			}
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> adminGetReimbursementsByStatus(int statusId) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try (Connection conn = connectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursment ORDER BY reimb_id desc";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reimbursements.add(extractTable(rs));
			}
			for (Reimbursement r : reimbursements) {
				pullFullName(r, true);
				pullFullName(r, false);
			}
			return reimbursements;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean adminUpdate(int userId, int statusId, int reimbId) {
		try (Connection conn = connectionUtil.getConnection()) {
			String sql = "UPDATE ers_reimbursment SET reimb_resolved = CURRENT_TIMESTAMP, reimb_resolver = ?, reimb_status_id = ? WHERE reimb_id = ?";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);
			ps.setInt(2, statusId);
			ps.setInt(3, reimbId);

			int result = ps.executeUpdate();
			if (result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

}
