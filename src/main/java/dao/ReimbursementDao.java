package dao;

import java.sql.Timestamp;
import java.util.List;

import models.Reimbursement;

/**
 * An interface for all Reimbursement methods that handle SQL queries
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
	 * @param userId = the user ID of an employee (not a manager)
	 * @return return the list reimbursements
	 */
	List<Reimbursement> getReimbursementsById(int userId);
	

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
	 * @return true if updated, false if note updated
	 */
	boolean postReimbursementToDataBase(int amount, String description, int author,  int statusId, int typeId);
	

	/************************************************
	 * 	Admin get and update (post) requests		*
	 ***********************************************/
	/**
	 * Get all reimbursements for the admin user
	 * @return return a list of reimbursements
	 */
	List<Reimbursement> adminGetReimbursements();
	
	/**
	 * Get all reimbursements for the admin user and displays information based on their status
	 * @param status = the status by number ID
	 * @return return the list reimbursements
	 */
	List<Reimbursement> adminGetReimbursementsByStatus(int statusId);
	
	/**
	 * This is for updating a row in the database
	 * @param userId = the userId of the admin
	 * @param statusId = the status (2 if approved, 3 if denied)
	 * @param reimbId = the id for the reimbursement that is being updated
	 * @return true if updated, false if note updated
	 */
	boolean adminUpdate(int userId, int statusId, int reimbId);
	
}
