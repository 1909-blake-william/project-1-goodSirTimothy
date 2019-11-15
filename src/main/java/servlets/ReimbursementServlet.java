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
		System.out.println(user);
		if ("EMPLOYEE".contentEquals(user.getRole())) {
			List<Reimbursement> reimbursements = reimbDao.getReimbursementsById(user.getUserId());
			ObjectMapper om = new ObjectMapper();
			String json = om.writeValueAsString(reimbursements);
			resp.getWriter().write(json);
		} else if ("MANAGER".contentEquals(user.getRole())){
			
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Reimbursement credentials = (Reimbursement) om.readValue(req.getReader(), Reimbursement.class);
		System.out.println(credentials);
		boolean result = reimbDao.postReimbursementToDataBase(credentials.getAmount(), credentials.getDescription(),
				credentials.getAuthor(), credentials.getStatusId(), credentials.getTypeId());
		System.out.println("The Insert Results = " + result);
		if (result == false) {
			resp.setStatus(401); // Unauthorized status code
			return;
		} else {
			resp.setStatus(201);
			return;
		}
	}
}
