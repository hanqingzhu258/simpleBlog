package servlet;


import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;
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

/*@WebServlet(
		name="RegisterServlet",
		urlPatterns={"/register"}
	)*/
public class RegisterServlet  extends HttpServlet{
	private UserDao userDao;
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		
		userDao=DaoFactory.getUserDao();
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String password2=request.getParameter("password2");
		String nickname=request.getParameter("nickname");
		String email=request.getParameter("email");
		//String birthday=request.getParameter("birthday");
		if(Validator.hasNull(username,password,password2,nickname,email)){
			request.setAttribute("msgType",MessageType.ERROR);
			request.setAttribute("msg", "字段不能为空");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		if(!Objects.equals(password, password2)){
			request.setAttribute("msgType", MessageType.ERROR);
			request.setAttribute("msg","两次密码不相同");
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		User user=new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setNickname(nickname);
		user.setEmail(email);
		//user.setBirthday(new Date(System.currentTimeMillis()));
		//user.setRegisterTime(new Date(System.currentTimeMillis()));
		try{
			userDao.add(user);
			Optional<User>u=userDao.find(username);
			if(u.isPresent()){
				user=u.get();
				request.getSession().setAttribute("user", user);
			}
			response.sendRedirect("login.jsp");
		}catch(RuntimeException e){
			if(e.getMessage().contains("Duplicate entry")){
				request.setAttribute("msgType", MessageType.ERROR);
				request.setAttribute("msg","用户名不能重复");
				request.getRequestDispatcher("register.jsp").forward(request, response);
			}else{
				throw e;
			}
		}
	}
	
	@Override
	public void destroy(){
		super.destroy();
	}
	
	@Override
	public  void init( ) throws ServletException{
		userDao=DaoFactory.getUserDao();
	}

}
