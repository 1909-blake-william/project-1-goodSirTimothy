package dao;

import java.sql.Timestamp;
import java.util.List;

import models.Reimbursement;

public interface AdminReimbursementDao {
	/************************************************
	 * User get requests *
	 ***********************************************/
	/**
	 * SQL queries for display all reimbursements.
	 * 
	 * @param userId
	 * @param admin
	 * @return
	 */
	List<Reimbursement> getReimbursementsAll(String admin);

	/**
	 * 
	 * @param status
	 * @param userId
	 * @param admin
	 * @return
	 */
	List<Reimbursement> getReimbursementsByStatus(String status, String admin);

	/**
	 * 
	 * @param status
	 * @param admin
	 * @return
	 */
	List<Reimbursement> getReimbursementsByEmployeeId(String status, String admin);

	/**
	 * 
	 * @param date
	 * @param admin
	 * @return
	 */
	List<Reimbursement> getReimbursementsByDate(Timestamp date, String admin);

	/**
	 * 
	 * @param date
	 * @param admin
	 * @return
	 */
	List<Reimbursement> getReimbursementsBetweenDate(Timestamp date, String admin);

	/************************************************
	 * User update requests *
	 ***********************************************/
	/**
	 * 
	 * @param id
	 * @param resolved
	 * @param resolver
	 * @param statusId
	 * @param admin
	 * @return
	 */
	boolean updateReimbursements(int id, Timestamp resolved, int resolver, int statusId, String admin);
}
