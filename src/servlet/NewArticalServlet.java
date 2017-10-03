package servlet;

import java.util.List;
import java.awt.TrayIcon.MessageType;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDateTime;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;








import util.Validator;
import dao.ArticalDao;
import dao.DaoFactory;
import entity.Artical;
import entity.User;

import java.util.Iterator;
import java.util.stream.*;

//@WebServlet(name="NewArticalServlet",urlPatterns={"/newArtical"}
public class NewArticalServlet extends HttpServlet{
	private ArticalDao articalDao;
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		if(Validator.hasNull(title,content)){
			request.setAttribute("msgType",MessageType.ERROR);
			request.setAttribute("msg", "标题或者内容不能为空");
			request.getRequestDispatcher("newArtical.jsp").forward(request, response);
			return;
		}
		User user=(User)request.getSession().getAttribute("user");
		Artical artical=new Artical();
		artical.setTitle(title);
		artical.setContent(content);
		artical.setAuthor(user);
		//artical.setCreateTime(new Date(System.currentTimeMillis()));
		//artical.setModifyTime(new Date(System.currentTimeMillis()));
		articalDao.add(artical);
		/*artical=((Object)articalDao.listAllOf(user))
				.stream()
				.max(Comparator.comparing(Artical::getId))
				.get();  */
		
		List <Artical> articals=articalDao.listAllOf(user);
		int id=0;
		for(Artical artcial:articals){
			id=artical.getId();
		}

		response.sendRedirect("viewArtical?id="+id);
		id=0;
	}
	
	@Override
	public void init() throws ServletException{
		articalDao=DaoFactory.getArticalDao();
	}

}
