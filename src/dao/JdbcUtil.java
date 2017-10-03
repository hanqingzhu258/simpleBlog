package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcUtil {
	private static Connection connection;
	private static final String url="jdbc:mysql://127.0.0.1:3306/how2java?characterEncording=UTF-8";
	private static final String user="root";
	private static final String passwd="admin";
	
	private JdbcUtil(){
		
	}
	
	public static Connection getConnection(){
		if(connection==null){
			synchronized (JdbcUtil.class){
				if(connection==null){
					try{
						Class.forName("com.mysql.jdbc.Driver");
						connection=DriverManager.getConnection(url,user,passwd);
					}catch(SQLException | ClassNotFoundException e){
						throw new RuntimeException(e);
					}
					
				}
			}
		}
		return connection;
	}
}
