package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
@Autowired
public UserRepository userRepo;

public boolean addUser(User user){
	userRepo.save(user);
	return true;
}

public Iterable<User> getUsers(){
	return userRepo.findAll();
}

public User getUserbyAlias(String alias){
	return userRepo.findByAlias(alias);
}

public boolean updateUser(User userold,User usernew){
		userold.setAll(usernew);
		userRepo.save(userold);
	return true;
}
public boolean deleteUser(User user){
	User deleteuser = userRepo.findByAlias(user.getAlias());
	userRepo.delete(deleteuser);
	return true;
}

	
}
