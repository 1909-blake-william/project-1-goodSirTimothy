package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserDao;
import models.User;

public class LoginServlet extends HttpServlet {

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
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("/Project1/auth/login".equals(req.getRequestURI())) {
			User credentials = (User) om.readValue(req.getReader(), User.class);
			User loggedInUser = userDao.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword());
			if (loggedInUser == null) {
				resp.setStatus(401); // Unauthorized status code
				return;
			} else {
				System.out.println(loggedInUser);
				resp.setStatus(201);
				req.getSession().setAttribute("user", loggedInUser);
				resp.getWriter().write(om.writeValueAsString(loggedInUser));
				return;
			}
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if ("/Project1/auth/logout".equals(req.getRequestURI())) {
			req.getSession().invalidate();
		}
		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(req.getSession().getAttribute("user"));
		if("null".equals(json) || null == json) {
			System.out.println("Failed doGet request, because json is: " + json);
			resp.setStatus(401); // Unauthorized status code
		} else {
			if ("/Project1/auth/session-user".equals(req.getRequestURI())) {
				System.out.println("returning user information.");
				resp.getWriter().write(json);
			} else {
				System.out.println("Failed for unknown reason");
			}
		}
	}
}
