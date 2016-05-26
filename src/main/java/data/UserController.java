package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {
	@Autowired
	private UserService userService;

	@RequestMapping(value = "/users", method = RequestMethod.GET)
	public Iterable<User> getUsers() {
		return userService.getUsers();
	}

	@RequestMapping(value = "/users", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addUser(@RequestBody User user) {
		userService.addUser(user);
		return new ResponseEntity<Boolean>(true, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/users/login", method = RequestMethod.POST)
	public User login(@RequestParam String username, @RequestParam String pass) {
		User user = userService.getUserbyAlias(username);
		if (user == null) {
			user = new User("login_incorrecto",null);
		} else {
			if (!user.getPass().equals(pass)) {
				user = new User("login_incorrecto",null);
			}
		}
		return user;
	}

	@RequestMapping(value = "/user/{alias}", method = RequestMethod.GET)
	public User getUserbyAlias(@PathVariable("alias") String alias) {
		return userService.getUserbyAlias(alias);
	}
	
	@RequestMapping(value = "/user/{alias}", method = RequestMethod.PUT)
	public ResponseEntity<Boolean> updateUserbyAlias(@PathVariable("alias") String alias, @RequestBody User user) {
		
		User userold = userService.getUserbyAlias(alias);
		userService.updateUser(userold,user);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
	@RequestMapping(value = "/user/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> deleteUserbyAlias(@RequestBody User user) {
		
		userService.deleteUser(user);
		return new ResponseEntity<Boolean>(true, HttpStatus.OK);
	}
}
