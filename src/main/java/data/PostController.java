package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {
	
	@Autowired
	private PostService postService;
	
	
	
	@RequestMapping(value = "/postsimage", method = RequestMethod.GET)
	public Iterable<PostImage> getPostsImage(){
			return this.postService.getPostsImage();
	}
	@RequestMapping(value = "/postsimage/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> deletePostImage(@RequestBody PostImage post){
		postService.deleteImage(post);	
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/poststext", method = RequestMethod.GET)
	public Iterable<PostText> getPostsText(){
			return this.postService.getPostsText();
	}
	
	@RequestMapping(value = "/poststext/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> deletePostText(@RequestBody PostText post){
		postService.deleteText(post);	
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/postsvideo", method = RequestMethod.GET)
	public Iterable<PostVideo> getPostsVideo(){
			return this.postService.getPostsVideo();
	}
	
	@RequestMapping(value = "/postsvideo/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> deletePostVideo(@RequestBody PostVideo post){
		postService.deleteVideo(post);	
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/postsmusic", method = RequestMethod.GET)
	public Iterable<PostMusic> getPostsMusic(){
			return this.postService.getPostsMusic();
	}
	
	@RequestMapping(value = "/postsmusic/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> deletePostMusic(@RequestBody PostMusic post){
		postService.deleteMusic(post);	
		return new ResponseEntity<Boolean>(true,HttpStatus.OK);
	}
	
	@RequestMapping(value = "/postsimage", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addPostImage(@RequestBody PostImage post){
		postService.addPostImage(post);
		return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/poststext", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addPostText(@RequestBody PostText post){
		postService.addPostText(post);
		return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/postsvideo", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addPostVideo(@RequestBody PostVideo post){
		postService.addPostVideo(post);
		return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value = "/postsmusic", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addPostMusic(@RequestBody PostMusic post){
		postService.addPostMusic(post);
		return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		
	}
	
	@RequestMapping(value="/postsimage/{author}", method = RequestMethod.GET)
	public Iterable<PostImage> getPostsImageAuthor(@PathVariable("author") String author){
		return this.postService.getPostsImage(author);
	}
	
	@RequestMapping(value="/poststext/{author}", method = RequestMethod.GET)
	public Iterable<PostText> getPostsTextAuthor(@PathVariable("author") String author){
		return this.postService.getPostsText(author);
	}
	
	@RequestMapping(value="/postvideo/{author}", method = RequestMethod.GET)
	public Iterable<PostVideo> getPostsVideoAuthor(@PathVariable("author") String author){
		return this.postService.getPostsVideo(author);
	}
	
	@RequestMapping(value="/postmusic/{author}", method = RequestMethod.GET)
	public Iterable<PostMusic> getPostsMusicAuthor(@PathVariable("author") String author){
		return this.postService.getPostsMusic(author);
	}
}
