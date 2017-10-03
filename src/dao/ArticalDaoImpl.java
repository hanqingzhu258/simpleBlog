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

public class ArticalDaoImpl  implements ArticalDao{
	private Connection connection;
	
	public ArticalDaoImpl(){
		connection=JdbcUtil.getConnection();
	}
	
	@Override
	public void add(Artical artical){
		String sql="insert into artical values(null,?,?,?)";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
			statement.setString(1,artical.getTitle());
			statement.setString(2,artical.getContent());
			//statement.setObject(3,artical.getCreateTime());
			//statement.setObject(4,artical.getModifyTime());
			statement.setInt(3,artical.getAuthor().getId());
			statement.executeUpdate();
		}
		catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public Optional<Artical>find(int id){
		Artical artical=null;
		String sql="select id,title,content,author from artical where id=?";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
			statement.setInt(1, id);
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				artical=new Artical();
				artical.setId(rs.getInt(id));
				artical.setTitle(rs.getString(2));
				artical.setContent(rs.getString(3));
				UserDao userDao=DaoFactory.getUserDao();
				Optional<User>user=userDao.find(rs.getInt(4));
				//user.ifPresent(artical::setAuthor);
				//artical.setCreateTime(rs.getObject(5,Date.class));
				//artical.setModifyTime(rs.getObject(6,Date.class));
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return Optional.ofNullable(artical);
	}
	
	@Override
	public void update(Artical artical){
		String sql="update artical set title=?,content=?  where id=?";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
			statement.setString(1,artical.getTitle());
			statement.setString(2,artical.getContent());
			//statement.setObject(3,artical.getModifyTime());
			statement.setInt(3,artical.getId());
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public void delete(int id){
		String sql="delete from artical where id=?";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
			statement.setInt(1, id);
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	
	@Override
	public List<Artical>listAllOf(User author){
		List<Artical>articals=new ArrayList<>();
		String sql="select id,title,content from artical where author=?";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
			statement.setInt(1,author.getId());
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				Artical artical=new Artical();
				artical.setId(rs.getInt(1));
				artical.setTitle(rs.getString(2));
				artical.setContent(rs.getString(3));
				//artical.setCreateTime(rs.getObject(4,Date.class));
				//artical.setModifyTime(rs.getObject(5,Date.class));
				artical.setAuthor(author);
				
				CommentDao commentDao=DaoFactory.getCommentDao();
				List<Comment>comments=commentDao.listAllOf(artical);
				artical.setComments(comments);
				articals.add(artical);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return articals;
	}
	
	@Override
	public List<Artical>listAll(){
		List<Artical>articals=new ArrayList<>();
		String sql="select id,title,content,author from artical";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
		
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				Artical artical=new Artical();
				artical.setId(rs.getInt(1));
				artical.setTitle(rs.getString(2));
				artical.setContent(rs.getString(3));
				//artical.setCreateTime(rs.getObject(4,Date.class));
				//artical.setModifyTime(rs.getObject(5,Date.class));
				UserDao userDao=DaoFactory.getUserDao();
				Optional<User>user=userDao.find(rs.getInt(4));
				//user.ifPresent(artical::setAuthor);
				CommentDao commentDao=DaoFactory.getCommentDao();
				List<Comment>comments=commentDao.listAllOf(artical);
				artical.setComments(comments);
				articals.add(artical);
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return articals;
	}	
}
