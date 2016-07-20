package data;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
	
	User findByAlias(String alias);
	List<User> findAll();
	
}
