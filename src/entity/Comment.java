package entity;

import java.sql.Date;



public class Comment {
	private int id;
	private String content;
	private Date createTime;
	private User author;
	private Artical artical;
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	
	public void setContent(String content){
		this.content=content;
	}
	public String getContent(){
		return content;
	}
	
	public void setCreateTime(Date createTime){
		this.createTime=createTime;
	}
	public Date getCreateTime(){
		return createTime;
	}
	
	public void setAuthor(User author){
		this.author=author;
	}
	public User getAuthor(){
		return author;
	}
	
	public void setArtical(Artical artical){
		this.artical=artical;
	}
	public Artical getArtical(){
		return artical;
	}
}
