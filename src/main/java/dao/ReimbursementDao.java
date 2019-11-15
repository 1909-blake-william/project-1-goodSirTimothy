package dao;

import java.sql.Timestamp;
import java.util.List;

import models.Reimbursement;

/**
 * 
 * @author Tim Clifton
 *
 */
public interface ReimbursementDao {
	ReimbursementDao currentImplementation = new ReimbursementDaoImpl();
	/************************************************
	 * 	User get requests							*
	 ***********************************************/
	/**
	 * Get the reimbursements by the user ID
	 * @param userId
	 * @return
	 */
	List<Reimbursement> getReimbursementsById(int userId);
	
	/**
	 * Get the reimbursements by the user ID and their status
	 * @param status
	 * @param userId
	 * @return
	 */
	List<Reimbursement> getReimbursementsByStatus(String status, int userId);
	

	/************************************************
	 * 	User post requests							*
	 ***********************************************/
	/**
	 * Post a new reimbursements request.
	 * @param amount = the being requested for reimbursement 
	 * @param submitted = the date the reimbursement was submitted
	 * @param description = why the user needed a reimbursements
	 * @param author = the user that submitted the reimbursement form
	 * @param statusId = the status ID should be (1)
	 * @param typeId = the type ID should be (1)
	 * @return
	 */
	boolean postReimbursementToDataBase(int amount, String description, int author,  int statusId, int typeId);
	

	/************************************************
	 * 	Admin get and update (post) requests		*
	 ***********************************************/
	/**
	 * Get all reimbursements for the admin user
	 * @return
	 */
	List<Reimbursement> adminGetReimbursements();
	
	/**
	 * Get all reimbursements for the admin user and displays information based on their status
	 * @param status = the status by number ID
	 * @return
	 */
	List<Reimbursement> adminGetReimbursementsByStatus(int statusId);
	
	boolean adminUpdate(int userId, int statusId, int reimbId);
	
}
