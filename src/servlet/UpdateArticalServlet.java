package servlet;

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

//@WebServlet(name="UpdateArticalServlet",urlPatterns={"/updateArtical"})
public class UpdateArticalServlet  extends HttpServlet{
	private ArticalDao articalDao;
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		String title=request.getParameter("title");
		String content=request.getParameter("content");
		int id=Integer.parseInt(request.getParameter("id"));
		if(Validator.hasNull(title,content)){
			return;
		}
		Artical artical=articalDao.find(id).get();
		artical.setTitle(title);
		artical.setContent(content);
		//artical.setModifyTime(new Date(System.currentTimeMillis()));
		articalDao.update(artical);
		response.sendRedirect("viewArtical?id="+id);
	}
	
	@Override
	public void init() throws ServletException{
		articalDao=DaoFactory.getArticalDao();
	}
	
}
