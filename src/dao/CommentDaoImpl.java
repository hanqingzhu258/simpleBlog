package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import entity.Artical;
import entity.Comment;
import entity.User;

public class CommentDaoImpl  implements CommentDao{
	private Connection connection;
	public CommentDaoImpl(){
		connection=JdbcUtil.getConnection();
	}
	
	@Override
	public void add(Comment comment){
		String sql="insert into comment values (null,?,?,?)";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
			statement.setString(1,comment.getContent());
			statement.setInt(2,comment.getAuthor().getId());
			statement.setInt(3,comment.getArtical().getId());
			//statement.setObject(4,comment.getCreateTime());
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Comment>listAllOf(Artical artical){
		List<Comment>comments=new ArrayList<>();
		String sql="select id,content,author,artical from comment where artical=?";
		try(PreparedStatement statement =connection.prepareStatement(sql)){
			statement.setInt(1,artical.getId());
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				Comment comment=new Comment();
				comment.setId(rs.getInt(1));
				comment.setContent(rs.getString(2));
				UserDao userDao=DaoFactory.getUserDao();
				Optional<User>user=userDao.find(rs.getInt(3));
				//user.ifPresent(comment::setAuthor);
				comment.setArtical(artical);
				//comment.setCreateTime(rs.getObject(5,Date.class));
				comments.add(comment);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return comments;
	}
	
	@Override
	public void delete(int id){
		String sql="delete from comment where id=?";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
			statement.setInt(1, id);
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public List<Comment>listAll(){
		List<Comment>comments=new ArrayList<>();
		String sql="select id,content,author,artical from comment";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
			ResultSet rs =statement.executeQuery();
			while(rs.next()){
				Comment comment=new  Comment();
				comment.setId(rs.getInt(1));
				comment.setContent(rs.getString(2));
				UserDao userDao=DaoFactory.getUserDao();
				Optional<User>user=userDao.find(rs.getInt(3));
				//user.ifPresent(comment::setAuthor);
				ArticalDao articalDao=DaoFactory.getArticalDao();
				Optional<Artical>article=articalDao.find(rs.getInt(4));
				//article.ifPresent(comment::setArtical);
				//comment.setCreateTime(rs.getObject(5,Date.class));
				comments.add(comment);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return comments;
	}
}
