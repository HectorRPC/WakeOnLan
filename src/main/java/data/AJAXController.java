package data;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.hibernate.mapping.Array;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
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
			@RequestMapping(value="/ajax/aulas", method=RequestMethod.POST)
			public @ResponseBody AjaxResponseBody getAulasAularioAJAX(@RequestBody Search search){
				AjaxResponseBody result = new AjaxResponseBody();
				Aulario aular = wolService.getAularioNombre(search.getAulario());
				Iterable<Aula> aulas = wolService.getAulasAulario(aular);
				ArrayList<Integer> aulasNumero = new ArrayList<Integer>();
				for(Aula aula: aulas){
					aulasNumero.add(aula.getNumero());
				}
				wolService.getAulasAulario(aular);
				result.setCode("200");
				result.setMsg("");
				result.setAulas(aulasNumero);
				return result;
			}
		
}