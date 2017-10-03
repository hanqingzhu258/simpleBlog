package servlet;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





import util.Validator;
import dao.ArticalDao;
import dao.CommentDao;
import dao.DaoFactory;
import entity.Artical;
import entity.Comment;

//@WebServlet(name = "ViewArticleServlet", urlPatterns = {"/viewArticle"})
public class ViewArticalServlet extends HttpServlet {
    private ArticalDao articalDao;
    private CommentDao commentDao;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        try {
            String _page = request.getParameter("page");
            if (Validator.hasNull(_page))
                _page = "1";
            page = Integer.valueOf(_page);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
       try {
            int id = Integer.valueOf(request.getParameter("id"));

            Optional<Artical> artical = articalDao.find(id);
            request.setAttribute("artical", artical.get());
            List<Comment> comments = commentDao.listAllOf(artical.get());
            request.setAttribute("comments", comments);
            //评论分页
            int commentsPerPage = 20;
            int totalComments = comments.size();
           int totalPages=totalComments % commentsPerPage==0 ? totalComments / commentsPerPage : totalComments / commentsPerPage+1;
           totalPages=totalPages==0 ? 1 : totalPages;
            int beginIndex=(page-1)*commentsPerPage;
            int endIndex=beginIndex+commentsPerPage;
            endIndex=endIndex > totalComments ? totalComments : endIndex;
            request.setAttribute("page",page);
            request.setAttribute("beginIndex",beginIndex);
            request.setAttribute("endIndex",endIndex);
            request.setAttribute("totalPages",totalPages);
            request.getRequestDispatcher("viewArtical.jsp").forward(request,response); 
        }catch(Exception e){
        	response.sendError(HttpServletResponse.SC_NOT_FOUND,"页码不存在");
        }
       /* int id = Integer.valueOf(request.getParameter("id"));

        Optional<Artical> artical = articalDao.find(id);
        request.setAttribute("artical", artical.get());
        request.getRequestDispatcher("viewArtical.jsp").forward(request,response); */
    }
    
    @Override
    public void init() throws ServletException{
    	super.init();
    	articalDao=DaoFactory.getArticalDao();
    	commentDao=DaoFactory.getCommentDao();
    }
    
}