package servlet;

import java.io.IOException;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






import java.util.List;

import util.Validator;
import dao.ArticalDao;
import dao.CommentDao;
import dao.DaoFactory;
import dao.UserDao;
import entity.Artical;
import entity.Comment;
import entity.User;

//@	WebServlet(name="ViewUserServlet",urlPatterns={"/viewUser"})
public class ViewUserServlet  extends HttpServlet{
	private UserDao userDao;
	private ArticalDao articalDao;
	private CommentDao commentDao;
	private static final int articalsPerPage=20;
	
	@Override 
	protected void service (HttpServletRequest request ,HttpServletResponse response)throws ServletException,IOException{
		int id=Integer.valueOf(request.getParameter("id"));
		Optional<User>u=userDao.find(id);
		String _page=request.getParameter("page");
		int page=1;
		if(Validator.hasNull(_page)){
			_page="1";
		}
		try{
			page=Integer.valueOf(_page);
		}catch(Exception e){
			response.sendError(HttpServletResponse.SC_NOT_FOUND,"页码不存在");
			return;
		}
		if(u.isPresent()){
		/*	long articalCounts=( articalDao.listAll())
					.stream()
					.filter(artical->artical.getAuthor().getId()==u.get().getId())
					.count();
		*/
			List<Artical>articals=articalDao.listAll();
			long articalCounts=0;
			for(Artical artical:articals){
				articalCounts+=1;
			}
			
			
		/*	long commentCounts = commentDao.listAll()
                    .stream()
                    .filter(comment -> comment.getAuthor().getId() == u.get().getId())
                    .count();
		*/
			List<Comment>comments=commentDao.listAll();
			long commentCounts=0;
			for(Comment comment:comments){
				commentCounts+=1;
			}
			
			
      /*      long commentedCounts = articalDao.listAll()
                    .stream()
                    .filter(artical -> artical.getAuthor().getId() == u.get().getId())
                    .mapToLong(artical -> artical.getComments().size())
                    .sum();
       */   
		List<Artical>articals2=articalDao.listAll();
		long commentedCounts=-1;
		/*for(Artical artical:articals){
			int count=
		}*/
		
			
			
             
            long totalPage = articalCounts % articalsPerPage == 0 ? articalCounts / articalsPerPage : articalCounts / articalsPerPage + 1;
            //不管有几篇文章，最起码得有一页
            if(totalPage==0){
            	totalPage=1;
            }
            if(page>totalPage){
            	response.sendError(HttpServletResponse.SC_NOT_FOUND,"页码不存在");
            	return;
            }
            request.setAttribute("page", page);
            request.setAttribute("articalsPerPage", articalsPerPage);
            request.setAttribute("user", u.get());
            request.setAttribute("articalCounts", articalCounts);
            request.setAttribute("commentCounts",commentCounts);
            request.setAttribute("commentedCounts", commentedCounts);
            request.setAttribute("articals", articalDao.listAllOf(u.get()));
            request.getRequestDispatcher("viewUser.jsp").forward(request, response);
            
    		articalCounts=0;
    		commentCounts=0;
    		commentedCounts=0;
            
		}else{
			response.sendError(HttpServletResponse.SC_NOT_FOUND,"您查找的用户不存在");
		}

	}

	@Override
	public void init() throws ServletException{
		userDao=DaoFactory.getUserDao();
		articalDao=DaoFactory.getArticalDao();
		commentDao=DaoFactory.getCommentDao();
	}


}
