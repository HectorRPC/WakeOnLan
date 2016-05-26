package data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PostTextRepository extends CrudRepository<PostText,Long> {
	
	List<PostText> findByAuthor(String author);
}
