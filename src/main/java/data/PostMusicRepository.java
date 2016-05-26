package data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PostMusicRepository extends CrudRepository<PostMusic,Long> {
	
	List<PostMusic> findByAuthor(String author);
}