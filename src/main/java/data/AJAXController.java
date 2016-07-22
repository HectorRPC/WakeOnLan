package data;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class AJAXController {
	
	@Autowired
	private WakeOnLanService wolService;
	@Autowired
	private UserService userService;
	
	
	//Devuelve una lista de las aulas de un aulario (AJAX)
			@RequestMapping(value="/ajax/aulas", method = RequestMethod.POST)
			public AjaxResponseBody getAulasAularioAJAX(@RequestBody String aulario){
				AjaxResponseBody result = new AjaxResponseBody();
				Aulario aular = wolService.getAularioNombre(aulario);
				result.setCode("200");
				result.setMsg("");
				result.setAulas((List<Aula>) wolService.getAulasAulario(aular));
				return result;
			}
		
}