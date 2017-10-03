package entity;

import java.sql.Date;

/*import java.time.LocalDate;
import java.time.LocalDateTime;*/

public class User {
	private int id;
	private String username;
	private String password;
	private String nickname;
	private String email;
	// LocalDate birthday;
	//private LocalDateTime registerTime;
	
	private Date birthday;
	private Date registerTime;
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	
	public void setUsername(String username){
		this.username=username;
	}
	public String getUsername(){
		return username;
	}
	
	public void setPassword(String password){
		this.password=password;
	}
	public String getPassword(){
		return password;
	}
	
	public void setNickname(String nickname){
		this.nickname=nickname;
	}
	public String getNickname(){
		return nickname;
	}
	
	public void setEmail(String email){
		this.email=email;
	}
	public String getEmail(){
		return email;
	}
	
	public void setBirthday(Date birthday){
		this.birthday=birthday;
	}
	public  Date getBirthday(){
		return birthday;
	}
	
	public void setRegisterTime(Date registerTime){
		this.registerTime=registerTime;
	}
	public Date getRegisterTime(){
		return registerTime;
	}
}
