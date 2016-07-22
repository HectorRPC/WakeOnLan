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
	
	
		
		
	//Devuelve la página de inicio
	@RequestMapping(value = "/")
	public ModelAndView index(HttpSession session){
		return new ModelAndView("index_template");
	}
		
	//Menu
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public ModelAndView menu(HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return new ModelAndView("menu");
		}else{
			return new ModelAndView("index_template");
		}
	}
		
	//Devuelve una lista de todos los ordenadores
	@RequestMapping(value = "/ordenadores/todos", method = RequestMethod.GET)
	public ModelAndView getOrdenadores(HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return new ModelAndView("lista_ordenadores_template").addObject("ordenadores", this.wolService.getOrdenadores());
		}else{
			return new ModelAndView("index_template");
		}
	}
	
	//Lista de ordenadores por nombre
		@RequestMapping(value = "/ordenadores", method = RequestMethod.GET)
		public ModelAndView getOrdenadoresByNombre(HttpSession session, @RequestParam String nombre){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				return new ModelAndView("lista_ordenadores_template").addObject("ordenadores", this.wolService.getOrdenadoresNombre(nombre));
			}else{
				return new ModelAndView("index_template");
			}
		}
		
	//Lista de ordenadores por ip
		@RequestMapping(value = "/ordenadores/ip", method = RequestMethod.GET)
		public ModelAndView getOrdenadoresByIp(HttpSession session, @RequestParam String ip){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				return new ModelAndView("lista_ordenadores_template").addObject("ordenadores", this.wolService.getOrdenadoresIP(ip));
			}else{
				return new ModelAndView("index_template");
			}
		}
		
	//Lista de ordenadores por mac
		@RequestMapping(value = "/ordenadores/mac", method = RequestMethod.GET)
		public ModelAndView getOrdenadoresByMac(HttpSession session, @RequestParam String mac){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				return new ModelAndView("lista_ordenadores_template").addObject("ordenadores", this.wolService.getOrdenadoresMAC(mac));
			}else{
				return new ModelAndView("index_template");
			}
		}
			
	//Detalles de un ordenador por Nombre de equipo
		@RequestMapping(value = "/ordenadores/{nombre_equipo}")
		public ModelAndView getDetalleOrdenador(HttpSession session, @PathVariable("nombre_equipo") String nombre_equipo){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				return new ModelAndView("detalle_ordenador_template").addObject("ordenador", this.wolService.getOrdenadorNombre(nombre_equipo));
			}else{
				return new ModelAndView("index_template");
			}
		}
		
	//Devuelve una lista de los ordenadores de un aula
	@RequestMapping(value="/aulas/{aula}", method = RequestMethod.GET)
	public ModelAndView getOrdenadorAula(@PathVariable("aula") int aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			Aula a = this.wolService.getAulasNumero(aula).iterator().next();
			return new ModelAndView("lista_ordenadores_template").addObject("ordenadores", this.wolService.getOrdenadoresAula(a));
		}else{
			return new ModelAndView("index_template");
		}
			
	}
	
	//Enciende un ordenador
		@RequestMapping(value = "/ordenadores/{nombre}/wake", method = RequestMethod.GET)
		public ModelAndView wakeOrdenador(@PathVariable("nombre") String nombre, HttpSession session){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				if (wolService.wakeOrdenador(nombre)){
					return new ModelAndView("actionPerformed").addObject("status", "exito");
				}else{
					return new ModelAndView("actionPerformed").addObject("status", "error");
				}
			}else{
				return new ModelAndView("index_template");
			}
		}
	
	//Elimina un ordenador
	@RequestMapping(value = "/ordenador/delete", method = RequestMethod.POST)
	public ModelAndView deleteOrdenador(@RequestParam String nombre, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			if (wolService.deleteOrdenador(nombre)){
				return new ModelAndView("actionPerformed").addObject("status", "exito");
			}else{
				return new ModelAndView("actionPerformed").addObject("status", "error");
			}
		}else{
			return new ModelAndView("index_template");
		}
	}
	
	//Formulario para añadir un ordenador
		@RequestMapping(value = "/ordenadores/addOrdenadorForm")
		public ModelAndView addOrdenadorForm(HttpSession session){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				return new ModelAndView("addOrdenadorForm_template").addObject("aularios", wolService.getAularios());
			}else{
				return new ModelAndView("index_template");
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
			return new ModelAndView("index_template");
		}
		
	}
	
	//Devuelve una lista de todas las aulas
	@RequestMapping(value = "/aulas", method = RequestMethod.GET)
	public ModelAndView getAulas(HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return new ModelAndView("aulas").addObject("aulas", this.wolService.getAulas());
		}else{
			return new ModelAndView("index_template");
		}
	}
	
	//Devuelve una lista de las aulas de un aulario
	@RequestMapping(value="/aulario/{aulario}", method = RequestMethod.GET)
	public ModelAndView getAulasAulario(@PathVariable("aulario") String aulario, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return new ModelAndView("aulas").addObject("aulas", this.wolService.getAularioNombre(aulario).getAulas());
		}else{
			return new ModelAndView("index_template");
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
			return new ModelAndView("index_template");
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
			return new ModelAndView("index_template");
		}
		
	}
	
	//Devuelve una lista de todos los aularios
		@RequestMapping(value = "/aularios", method = RequestMethod.GET)
		public ModelAndView getAularios(HttpSession session){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				return new ModelAndView("aularios").addObject("aularios", this.wolService.getAularios());
			}else{
				return new ModelAndView("index_template");
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
				return new ModelAndView("index_template");
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
				return new ModelAndView("index_template");
			}
		}
		
		
		
		
		
		
}