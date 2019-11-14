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

	@Override
	public List<Reimbursement> getReimbursementsById(int userId) {
		List<Reimbursement> reimbursements = new ArrayList<Reimbursement>();
		try (Connection conn = connectionUtil.getConnection()) {
			String sql = "SELECT * FROM ers_reimbursment";

			PreparedStatement ps = conn.prepareStatement(sql);

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("reimb_id");
				int amount = rs.getInt("reimb_amount");
				Timestamp submitted = rs.getTimestamp("reimb_submitted");
				Timestamp resolved = rs.getTimestamp("reimb_resolved");
				String description = rs.getString("reimb_description");
				int author = rs.getInt("reimb_author");
				int resolver = rs.getInt("reimb_resolver");
				int statusId = rs.getInt("reimb_status_id");
				int typeId = rs.getInt("reimb_type_id");
				reimbursements.add(new Reimbursement(id, amount, submitted, resolved, description, author, resolver,
						statusId, typeId));
			}
			for (Reimbursement r : reimbursements) {
				System.out.println(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Reimbursement> getReimbursementsByStatus(String status, int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean postReimbursementToDataBase(int amount, String description, int author, int statusId, int typeId) {
		try (Connection conn = connectionUtil.getConnection()) {
			String sql = "";
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

}
