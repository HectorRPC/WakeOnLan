package data;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class PostMusic {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	
	//Pelarse con la API de Goear o Spotify
	private String author;
	private String title;
	private Date date;	
	private String musicUrl;
	private String description; //max 250
	
	public PostMusic(){
		
	}
	public PostMusic(String author, String title, String musicUrl,
			String description) {
		super();
		this.author = author;
		this.title = title;
		this.date = new Date();
		this.musicUrl = musicUrl;
		this.description = description;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getMusicUrl() {
		return musicUrl;
	}
	public void setMusicUrl(String musicUrl) {
		this.musicUrl = musicUrl;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}
