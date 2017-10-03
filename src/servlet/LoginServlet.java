package servlet;



import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;









import util.MessageType;
import util.Validator;
import dao.DaoFactory;
import dao.UserDao;
import entity.User;

//@WebServlet(name="LoginServlet",urlPatterns={"/login"}
public class LoginServlet  extends  HttpServlet{
	private UserDao userDao;
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		if(Validator.hasNull(username,password)){
			request.setAttribute("msgType",MessageType.ERROR);
			request.setAttribute("msg", "用户名或密码不能为空");
			request.getRequestDispatcher("login.jsp");
			return;
		}
		
		Optional<User>user=userDao.find(username);
		if(user.isPresent()&&user.get().getPassword().equals(password)){
			request.getSession().setAttribute("user", user.get());
			response.sendRedirect("index.jsp");
		}else{
			request.setAttribute("msgType",MessageType.ERROR);
			request.setAttribute("msg", "用户名或密码不能为空");
			request.getRequestDispatcher("login.jsp").forward(request, response);
			//request.getRequestDispatcher("login.jsp")
		}
	}
	
	
	@Override
	public void init() throws ServletException{
		super.init();
		userDao=DaoFactory.getUserDao();
	}
	
	
}
