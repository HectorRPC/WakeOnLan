package data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PrivateMsg {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String author;
	private String title;
	private Date date;	
	private String body; //max 250
	private String target;	//destinatario, podemos ponerlo como referencia a un user.
	public PrivateMsg(){
		
	}
	public PrivateMsg(String author, String title, String body,
			String target) {
		super();
		this.author = author;
		this.title = title;
		this.date = new Date();
		this.body = body;
		this.target = target;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	
	
}
