package dao;

import java.util.List;

import entity.Artical;
import entity.Comment;

public interface CommentDao {
	void add(Comment comment);
	
	List<Comment>listAllOf(Artical artical);
	
	void delete(int id);
	
	List<Comment>listAll();
}

