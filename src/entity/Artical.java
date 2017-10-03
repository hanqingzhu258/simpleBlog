package entity;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Artical {
	private int id;
	private String title;
	private String content;
	private Date createTime;
	private Date modifyTime;
	private User author;
	private List<Comment>comments;
	
	public void setId(int id){
		this.id=id;
	}
	public int getId(){
		return id;
	}
	
	public void setTitle(String title){
		this.title=title;
	}
	public String getTitle(){
		return title;
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
	
	public void setModifyTime(Date modifyTime){
		this.modifyTime=modifyTime;
	}
	public Date getModifyTime(){
		return modifyTime;
	}
	
	public void setAuthor(User author){
		this.author=author;
	}
	public User getAuthor(){
		return author;
	}
	
	public void setComments(List<Comment> comments){
		this.comments=comments;
	}
	public List<Comment> getComments(){
		return comments;
	}
}
