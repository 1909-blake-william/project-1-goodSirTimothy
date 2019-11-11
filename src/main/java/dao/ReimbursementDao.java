package dao;

import java.sql.Timestamp;
import java.util.List;

import models.Reimbursement;

public interface ReimbursementDao {
	/************************************************
	 * 	User get requests							*
	 ***********************************************/
	/**
	 * 
	 * @param userId
	 * @return
	 */
	List<Reimbursement> getReimbursementsById(int userId);
	
	/**
	 * 
	 * @param status
	 * @param userId
	 * @return
	 */
	List<Reimbursement> getReimbursementsByStatus(String status, int userId);
	

	/************************************************
	 * 	User post requests							*
	 ***********************************************/
	/**
	 * 
	 * @param amount
	 * @param submitted
	 * @param description
	 * @param author
	 * @param statusId
	 * @param typeId
	 * @return
	 */
	boolean postReimbursementToDataBase(int amount, Timestamp submitted, String description, int author,  int statusId, int typeId);
	
	
}
