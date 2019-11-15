package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import models.Reimbursement;
import utils.connectionUtil;

public class ReimbursementDaoImpl implements ReimbursementDao {
	
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
		return new Reimbursement(id, amount, submitted, resolved, description, author, resolver,
				statusId, typeId);
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
				System.out.println(r);
			}
			return reimbursements;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByStatus(String status, int userId) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try (Connection conn = connectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursment WHERE REIMB_AUTHOR = ? AND REIMB_STATUS_ID = ? ORDER BY reimb_id desc";

			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, userId);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				reimbursements.add(extractTable(rs));
			}
			for (Reimbursement r : reimbursements) {
				System.out.println(r);
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
			String sql = "INSERT INTO ers_reimbursment(REIMB_ID, REIMB_AMOUNT, REIMB_SUBMITTED, " + 
					"REIMB_RESOLVED, REIMB_DESCRIPTION, REIMB_AUTHOR, REIMB_RESOLVER, REIMB_STATUS_ID, REIMB_TYPE_ID) " + 
					"VALUES (REIMB_ID_SEQ.nextval, ?, CURRENT_TIMESTAMP, null, ?, ?, null, ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, amount);
			ps.setString(2, description);
			ps.setInt(3, author);
			ps.setInt(4, statusId);
			ps.setInt(5, typeId);
			
			int result = ps.executeUpdate();
			
			if(result == 1) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public List<Reimbursement> adminGetReimbursements() {
		List<Reimbursement> reimbursements;
		try (Connection conn = connectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursment ORDER BY reimb_id desc";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> adminGetReimbursementsByStatus(int statusId) {
		List<Reimbursement> reimbursements;
		try (Connection conn = connectionUtil.getConnection()){
			String sql = "SELECT * FROM ers_reimbursment WHERE reimb_status_id = ? ORDER BY reimb_id desc";
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean adminUpdate(int userId, int statusId) {
		// TODO Auto-generated method stub
		return false;
	}

}
