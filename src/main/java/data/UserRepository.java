package data;


import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long> {
	
	User findByAlias(String alias);
	
}
