package dao;

import java.sql.Timestamp;
import java.util.List;

import models.Reimbursement;

public interface AdminReimbursementDao {
	/************************************************
	 * 	User get requests							*
	 ***********************************************/
	/**
	 * 
	 * @param userId
	 * @param admin
	 * @return
	 */
	List<Reimbursement> getReimbursementsById(int userId, String admin);
	
	/**
	 * 
	 * @param status
	 * @param userId
	 * @param admin
	 * @return
	 */
	List<Reimbursement> getReimbursementsByStatus(String status, int userId, String admin);
	

	/************************************************
	 * 	User update requests							*
	 ***********************************************/
	boolean updateReimbursements(int id, Timestamp resolved, int resolver, int statusId);
}
