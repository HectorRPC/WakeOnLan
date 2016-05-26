package data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PostVideoRepository extends CrudRepository<PostVideo,Long> {
	
	List<PostVideo> findByAuthor(String author);
}