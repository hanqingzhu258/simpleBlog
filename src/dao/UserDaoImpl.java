package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import entity.User;

public class UserDaoImpl implements UserDao{
	private Connection connection;
	
	public UserDaoImpl(){
		connection=JdbcUtil.getConnection();
	}
	
	@Override
	public void add(User user){
		String sql="insert into user values (null, ? , ? , ? , ? )";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
			statement.setString(1,user.getUsername());
			statement.setString(2,user.getPassword());
			statement.setString(3,user.getNickname());
			statement.setString(4,user.getEmail());
			//statement.setObject(5,user.getBirthday());
			//statement.setObject(6,user.getRegisterTime());
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public Optional<User>find (int id){
		User user=null;
		String sql="select id,username,password,nickname,email from user where id=?";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
			statement.setInt(1, id);
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				user=new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setNickname(rs.getString(4));
				user.setEmail(rs.getString(5));
				//user.setBirthday(rs.getObject(6,Date.class));
				//user.setRegisterTime(rs.getObject(7,Date.class));
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return Optional.ofNullable(user);

	}
	
	@Override
	public Optional<User>find(String username){
		User user=null;
		String sql="select id,username,password,nickname,email from user where username=?";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
			statement.setString(1,username);
			ResultSet rs=statement.executeQuery();
			while(rs.next()){
				user=new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setNickname(rs.getString(4));
				user.setEmail(rs.getString(5));
				//user.setBirthday(rs.getObject(6,Date.class));
				//user.setRegisterTime(rs.getObject(7,Date.class));
			}
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
		return Optional.ofNullable(user);
	}
	
	
	@Override
	public void delete(int id){
		String sql="delete from user where id=?";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
			statement.setInt(1, id);
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void update(User user){
		String sql="update user set password=?,nickname=?,email=? where id=?";
		try(PreparedStatement statement=connection.prepareStatement(sql)){
			statement.setString(1,user.getPassword());
			statement.setString(2,user.getNickname());
			statement.setString(3,user.getEmail());
			//statement.setObject(4,user.getBirthday());
			statement.setInt(4,user.getId());
			statement.executeUpdate();
		}catch(SQLException e){
			throw new RuntimeException(e);
		}
	}
	
	
}
