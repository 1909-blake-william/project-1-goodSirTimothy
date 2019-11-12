package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserDao;
import models.User;

public class UserServlet extends HttpServlet {

	UserDao userDao = UserDao.currentUser;

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
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
		System.out.println("request recived with uri: " + req.getRequestURI());

		User user;

		String username = req.getParameter("username");
		String password = req.getParameter("password");

		if (username != null) { // find by trainer name
			user = userDao.findByUsernameAndPassword(username, password);
		} else { // find all
			user = null;
		}

		ObjectMapper om = new ObjectMapper();
		String json = om.writeValueAsString(user);

		resp.addHeader("content-type", "application/json");
		resp.getWriter().write(json);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("uri = " + req.getRequestURI());
		if ("/Project1/login".equals(req.getRequestURI())) {
			ObjectMapper om = new ObjectMapper();
			User credentials = (User) om.readValue(req.getReader(), User.class);
			User loggedInUser = userDao.findByUsernameAndPassword(credentials.getUsername(), credentials.getPassword());
			if (loggedInUser == null) {
				resp.setStatus(401); // Unauthorized status code
				return;
			} else {
				resp.setStatus(201);
				req.getSession().setAttribute("user", loggedInUser);
				resp.getWriter().write(om.writeValueAsString(loggedInUser));
				return;
			}
		}
	}
}
