package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoFactory;
import dao.UserDao;
import entity.User;

//@WebServlet(name="MyPageServlet",urlPatterns={"/myPage"})
public class MyPageServlet  extends HttpServlet{
	private UserDao userDao;
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		User user=(User)request.getSession().getAttribute("user");
		response.sendRedirect("viewUser?id="+user.getId());
	}
	
	@Override
	public void init() throws ServletException{
		userDao=DaoFactory.getUserDao();
	}
    
}
