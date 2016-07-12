package data;

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
public class WakeOnLanController {
	
	@Autowired
	private WakeOnLanService wolService;
	@Autowired
	private UserService userService;
	
	
		
		
		
	//Devuelve una lista de todos los ordenadores
	@RequestMapping(value = "/ordenadores", method = RequestMethod.GET)
	public ModelAndView getOrdenadores(HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return new ModelAndView("listaOrdenadores").addObject("ordenadores", this.wolService.getOrdenadores());
		}else{
			return new ModelAndView("index");
		}
	}
	
	//Devuelve una lista de los ordenadores de un aula
	@RequestMapping(value="/ordenadores/{aula}", method = RequestMethod.GET)
	public ModelAndView getOrdenadorAula(@RequestParam("aula") int aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			Aula a = this.wolService.getAulasNumero(aula).iterator().next();
			return new ModelAndView("listaOrdenadores").addObject("ordenadores", this.wolService.getOrdenadoresAula(a));
		}else{
			return new ModelAndView("index");
		}
			
	}
	
	//Elimina un ordenador
	@RequestMapping(value = "/ordenador/delete", method = RequestMethod.POST)
	public ModelAndView deleteOrdenador(@RequestParam Ordenador ordenador, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			if (wolService.deleteOrdenador(ordenador)){
				return new ModelAndView("actionPerformed").addObject("status", "exito");
			}else{
				return new ModelAndView("actionPerformed").addObject("status", "error");
			}
		}else{
			return new ModelAndView("index");
		}
	}
	
	//Añade un ordenador
	@RequestMapping(value = "/ordenador", method = RequestMethod.POST)
	public ModelAndView addOrdenador(@RequestParam Ordenador ordenador, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			if (wolService.addOrdenador(ordenador)){
				return new ModelAndView("actionPerformed").addObject("status", "exito");
			}else{
				return new ModelAndView("actionPerformed").addObject("status", "error");
			}
		}else{
			return new ModelAndView("index");
		}
		
	}
	
	//Devuelve una lista de todas las aulas
	@RequestMapping(value = "/aulas", method = RequestMethod.GET)
	public ModelAndView getAulas(HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return new ModelAndView("aulas").addObject("aulas", this.wolService.getAulas());
		}else{
			return new ModelAndView("index");
		}
	}
	
	//Devuelve una lista de las aulas de un aulario
	@RequestMapping(value="/aulas/{aulario}", method = RequestMethod.GET)
	public ModelAndView getAulasAulario(@RequestParam("aulario") int aulario, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return new ModelAndView("aulas").addObject("aulas", this.wolService.getAulariosNumero(aulario).getAulas());
		}else{
			return new ModelAndView("index");
		}
			
	}
	
	//Elimina un aula
	@RequestMapping(value = "/aula/delete", method = RequestMethod.POST)
	public ModelAndView deleteAula(@RequestParam Aula aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			if (wolService.deleteAula(aula)){
				return new ModelAndView("actionPerformed").addObject("status", "exito");
			}else{
				return new ModelAndView("actionPerformed").addObject("status", "error");
			}
		}else{
			return new ModelAndView("index");
		}
	}
		
	//Añade un aula
	@RequestMapping(value = "/aula", method = RequestMethod.POST)
	public ModelAndView addAula(@RequestParam Aula aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			if (wolService.addAula(aula)){
				return new ModelAndView("actionPerformed").addObject("status", "exito");
			}else{
				return new ModelAndView("actionPerformed").addObject("status", "error");
			}
		}else{
			return new ModelAndView("index");
		}
		
	}
	
	//Devuelve una lista de todos los aularios
		@RequestMapping(value = "/aularios", method = RequestMethod.GET)
		public ModelAndView getAularios(HttpSession session){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				return new ModelAndView("aularios").addObject("aularios", this.wolService.getAularios());
			}else{
				return new ModelAndView("index");
			}
		}
		
		//Elimina un aulario
		@RequestMapping(value = "/aulario/delete", method = RequestMethod.POST)
		public ModelAndView deleteAulario(@RequestParam Aulario aulario, HttpSession session){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				if (wolService.deleteAulario(aulario)){
					return new ModelAndView("actionPerformed").addObject("status", "exito");
				}else{
					return new ModelAndView("actionPerformed").addObject("status", "error");
				}
			}else{
				return new ModelAndView("index");
			}
		}
		
		//Añade un aulario
		@RequestMapping(value = "/aulario", method = RequestMethod.POST)
		public ModelAndView addAulario(@RequestParam Aulario aulario, HttpSession session){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				if (wolService.addAulario(aulario)){
					return new ModelAndView("actionPerformed").addObject("status", "exito");
				}else{
					return new ModelAndView("actionPerformed").addObject("status", "error");
				}
			}else{
				return new ModelAndView("index");
			}
		}
		
		
		
}