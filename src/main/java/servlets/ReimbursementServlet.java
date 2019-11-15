package servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.ReimbursementDao;
import dao.ReimbursementDaoImpl;
import dao.UserDao;
import models.Reimbursement;
import models.User;

/**
 * 
 * @author Tim Clifton
 *
 */
public class ReimbursementServlet extends HttpServlet {

	ReimbursementDao reimbDao = ReimbursementDao.currentImplementation;
	UserDao userDao = UserDao.currentUser;
	ObjectMapper om = new ObjectMapper();

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		super.service(req, resp);

		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:5500");
		resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Headers",
				"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = userDao.currentUser();
		if ("EMPLOYEE".contentEquals(user.getRole())) {
			List<Reimbursement> reimbursements = reimbDao.getReimbursementsById(user.getUserId());
			String json = om.writeValueAsString(reimbursements);
			resp.getWriter().write(json);
		} else if ("MANAGER".contentEquals(user.getRole())) {
			List<Reimbursement> reimbursements = reimbDao.adminGetReimbursements();
			String json = om.writeValueAsString(reimbursements);
			resp.getWriter().write(json);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		boolean result = false;
		if ("/Project1/reimbursements/update".equals(req.getRequestURI())) {
			User user = userDao.currentUser();
			int reimbId = Integer.parseInt(req.getParameter("reimbId"));
			int statusId = Integer.parseInt(req.getParameter("status"));
			result = reimbDao.adminUpdate(user.getUserId(), statusId, reimbId);
		} else {
			Reimbursement reimbursement = (Reimbursement) om.readValue(req.getReader(), Reimbursement.class);
			System.out.println(reimbursement);
			result = reimbDao.postReimbursementToDataBase(reimbursement.getAmount(), reimbursement.getDescription(),
					reimbursement.getAuthor(), reimbursement.getStatusId(), reimbursement.getTypeId());
			System.out.println("The Insert Results = " + result);
		}
		if (result == false) {
			resp.setStatus(401); // Unauthorized status code
			return;
		} else {
			resp.setStatus(201);
			return;
		}
	}
}
