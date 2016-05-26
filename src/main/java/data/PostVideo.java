package data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class PostVideo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String author;
	private String title;
	private Date date;	
	private String videoUrl;
	private String description; //max 250
	
	public PostVideo(){
		
	}
	public String getAuthor() {
		return author;
	}
	
	public PostVideo(String author, String title, String videoUrl,
			String description) {
		super();
		this.author = author;
		this.title = title;
		this.date = new Date();
		this.videoUrl = videoUrl;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getVideoUrl() {
		return videoUrl;
	}
	public void setVideoUrl(String videoUrl) {
		this.videoUrl = videoUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
}
