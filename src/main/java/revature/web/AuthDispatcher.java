package revature.web;

import javax.servlet.http.HttpServletRequest;

public class AuthDispatcher implements Dispatcher{

	@Override
	public boolean supports(HttpServletRequest request) {
		return request.getRequestURI().startsWith("/api/") &&
				request.getRequestURI().contains("login") &&
				request.getMethod().equals("POST");
	}

	@Override
	public Object execute(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

}
