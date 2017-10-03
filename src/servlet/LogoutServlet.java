package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//@WebServlet(name="logoutServlet",urlPatterns={"/logout"})
public class LogoutServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		HttpSession session=request.getSession();
		session.invalidate();
		response.sendRedirect("index.jsp");
	}
	
	@Override
	public void init() throws ServletException{
		super.init();
	}

}
