package servlet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticalDao;
import dao.DaoFactory;
import entity.Artical;

//@WebServlet(name="EditArticalServlet",urlPatterns={"/editArtical"})
public class EditArticalServlet  extends HttpServlet{
	private ArticalDao articalDao;
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException,IOException{
		String _id=request.getParameter("id");
		int id=-1;
		try{
			id=Integer.parseInt(_id);
			Optional<Artical>artical=articalDao.find(id);
			request.setAttribute("artical", artical.get());
			request.getRequestDispatcher("editArtical.jsp").forward(request, response);
		}catch(NumberFormatException e){
			response.sendError(HttpServletResponse.SC_NOT_FOUND,"ÎÄÕÂ²»´æÔÚ");
		}
	}
	
	@Override
	public void init()throws ServletException{
		articalDao=DaoFactory.getArticalDao();
	}
}
