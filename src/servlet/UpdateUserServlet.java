package servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import util.MessageType;
import util.Validator;
import dao.DaoFactory;
import dao.UserDao;
import entity.User;

//@WebServlet(name="UpdateUserServlet",urlPatterns={"/updateUser"})
public class UpdateUserServlet  extends HttpServlet{
	private UserDao userDao;
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String nickname=request.getParameter("nickname");
		String birthday=request.getParameter("birthday");
		String email=request.getParameter("email");
		if(Validator.hasNull(nickname,birthday,email)){
			request.setAttribute("msgType", MessageType.ERROR);
			request.setAttribute("msg","用户信息不能为空");
			request.getRequestDispatcher("userInfo?index=0").forward(request, response);
			return;
		}
		User user=(User)request.getSession().getAttribute("user");
		user.setNickname(nickname);
		//user.setBirthday(new Date(System.currentTimeMillis()));
		user.setEmail(email);
		userDao.update(user);
		request.setAttribute("msgType", MessageType.ERROR);
		request.setAttribute("msg","用户信息修改成功");
		request.getRequestDispatcher("userInfo?index=0").forward(request, response);
	}
	
	@Override
	public void init() throws ServletException{
		userDao=DaoFactory.getUserDao();
	}
}
