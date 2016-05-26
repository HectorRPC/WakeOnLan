package data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PostImageRepository extends CrudRepository<PostImage,Long> {
	
	List<PostImage> findByAuthor(String author);
}
