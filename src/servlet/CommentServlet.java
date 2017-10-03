package servlet;

import java.io.IOException;


import java.sql.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ArticalDao;
import dao.CommentDao;
import dao.DaoFactory;
import entity.Comment;
import entity.User;

//@WebServlet(name="CommentServlet",urlPatterns={"/comment"})
public class CommentServlet extends HttpServlet {
	private CommentDao commentDao;
	private ArticalDao articalDao;
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
		int articalId=Integer.parseInt(request.getParameter("articalId"));
		User user=(User)request.getSession().getAttribute("user");
		String content=request.getParameter("comment");
		Comment comment=new Comment();
		comment.setContent(content);
		comment.setAuthor(user);
		comment.setArtical(articalDao.find(articalId).get());
		//comment.setCreateTime(new Date(System.currentTimeMillis()));
		commentDao.add(comment);
		response.sendRedirect("viewArtical?id="+articalId);
	}
	
	@Override
	public void init() throws ServletException {
		commentDao=DaoFactory.getCommentDao();
		articalDao=DaoFactory.getArticalDao();
	}

}
