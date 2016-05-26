package data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PrivateMsgRepository extends CrudRepository<PrivateMsg,Long> {
	
	List<PrivateMsg> findByAuthor(String author);
	List<PrivateMsg> findByTarget(String target);
}
