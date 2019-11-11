package servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import dao.UserDao;
import models.User;

public class UserServlet extends HttpServlet{
	
	UserDao userDao = UserDao.currentUser;
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.service(req, resp);
		System.out.println("To context param: " + req.getServletContext().getInitParameter("To"));

		resp.addHeader("Access-Control-Allow-Origin", "http://localhost:4200");
		// resp.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
		resp.addHeader("Access-Control-Allow-Methods", "GET");
		resp.addHeader("Access-Control-Allow-Headers",
				"Origin, Methods, Credentials, X-Requested-With, Content-Type, Accept");
		resp.addHeader("Access-Control-Allow-Credentials", "true");
		resp.setContentType("application/json");
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("request recived with uri: " + req.getRequestURI());
		// resp.getWriter().write("Hello from users servlet");
		
		// User user;

		String username = req.getParameter("username");
		System.out.println(username);
		boolean test = false;

		if (username != null) { // find by trainer name
			 test = userDao.findUsername(username);
		} else { // find all
			test = false;
		}

		//ObjectMapper om = new ObjectMapper();
		//String json = om.writeValueAsString("was the user found: " + test);
		String json = "was the user found: " + test;

		// resp.addHeader("content-type", "application/json");
		resp.getWriter().write(json);
	}
}
