package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet(name="UserInfoServlet",urlPatterns={"/userInfo"})
public class UserInfoServlet  extends HttpServlet{
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String index=request.getParameter("index");
		request.setAttribute("index", Integer.valueOf(index));
		request.getRequestDispatcher("me.jsp").forward(request, response);
	}
}
