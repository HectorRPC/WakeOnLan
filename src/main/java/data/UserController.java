package data;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class UserController {
	@Autowired
	private UserService userService;
	@Autowired
	private WakeOnLanService wolService;

	//Tras loguearse, devuelve el menú
	@RequestMapping(value="/login", method = RequestMethod.POST)
	public ModelAndView login(HttpSession session, @RequestParam String user, @RequestParam String pass){
		User usr = null;
		usr = userService.getUserbyAlias(user);
		if ((usr != null)&&(usr.getPass().equals(pass))) {
			session.setAttribute("logged", true);
			session.setAttribute("user", usr.getAlias());
			return new ModelAndView("login_success");
		}else{
			return new ModelAndView("index_template").addObject("status", "Usuario o contraseña incorrectos.");
		}
	}
			
	//Deslogueo
	@RequestMapping(value = "/logout")
	public ModelAndView logout(HttpSession session){
		session.setAttribute("logged", false);
		session.setAttribute("user", null);
		return new ModelAndView("index_template");
	}

	
}
