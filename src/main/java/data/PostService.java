package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {
	@Autowired
	private PostImageRepository postImageRepo;
	@Autowired
	private PostTextRepository postTextRepo;
	@Autowired
	private PostVideoRepository postVideoRepo;
	@Autowired
	private PostMusicRepository postMusicRepo;
	
	public Iterable<PostImage> getPostsImage(){
		return postImageRepo.findAll();
	}
	
	public Iterable<PostText> getPostsText(){
		return postTextRepo.findAll();
	}
	
	public Iterable<PostVideo> getPostsVideo(){
		return postVideoRepo.findAll();
	}
	
	public Iterable<PostMusic> getPostsMusic(){
		return postMusicRepo.findAll();
	}
	
	public boolean addPostImage(PostImage post){
		postImageRepo.save(post);
		return true;
	}
	
	public boolean addPostText(PostText post){
		postTextRepo.save(post);
		return true;
	}
	
	public boolean addPostVideo(PostVideo post){
		postVideoRepo.save(post);
		return true;
	}
	
	public boolean addPostMusic(PostMusic post){
		postMusicRepo.save(post);
		return true;
	}
	
	public Iterable<PostImage> getPostsImage(String author){
		return postImageRepo.findByAuthor(author);
	}
	
	public Iterable<PostText> getPostsText(String author){
		return postTextRepo.findByAuthor(author);
	}
	
	public Iterable<PostVideo> getPostsVideo(String author){
		return postVideoRepo.findByAuthor(author);
	}
	
	public Iterable<PostMusic> getPostsMusic(String author){
		return postMusicRepo.findByAuthor(author);
	}
	
	public boolean deleteText(PostText post){
		postTextRepo.delete(post);
		return true;
	}
	public boolean deleteMusic(PostMusic post){
		postMusicRepo.delete(post);
		return true;
	}
	public boolean deleteVideo(PostVideo post){
		postVideoRepo.delete(post);
		return true;
	}
	
	public boolean deleteImage(PostImage post){
		postImageRepo.delete(post);
		return true;
	}
	
}
