package servlet;


import java.io.IOException;
import java.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;




import util.MessageType;
import util.Validator;
import dao.DaoFactory;
import dao.UserDao;
import entity.User;

//@WebServlet(name="updateUserPasswordServlet",urlPatterns={"/updateUserPssword"})
public class UpdateUserPasswordServlet extends HttpServlet{
	private UserDao userDao;
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String password=request.getParameter("password");
		String password2=request.getParameter("password2");
		if(Validator.hasNull(password,password2)){
			request.setAttribute("msgType", MessageType.ERROR);
			request.setAttribute("msg", "密码不能为空");
			request.getRequestDispatcher("userInfo?index=1").forward(request, response);
			return;
		}
		if(!Objects.equals(password,password2)){
			request.setAttribute("msgType",MessageType.ERROR);
			request.setAttribute("msg", "两次密码不相同");
			request.getRequestDispatcher("userInfo?index=").forward(request, response);
			return;
		}
		User user=(User)request.getSession().getAttribute("user");
		user.setPassword(password);
		userDao.update(user);
		request.setAttribute("msgType", MessageType.ERROR);
		request.setAttribute("mag", "密码修改成功");
		request.getRequestDispatcher("userInfo?index=1").forward(request, response);
	}
	
	@Override
	public void init() throws ServletException{
		userDao=DaoFactory.getUserDao();
	}
}
