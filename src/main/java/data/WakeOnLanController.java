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
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return new ModelAndView("menu").addObject("aularios", wolService.getAularios());
		}else{
			return new ModelAndView("index_template");
		}
	}
		
	//Menu
	@RequestMapping(value = "/menu", method = RequestMethod.GET)
	public ModelAndView menu(HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return new ModelAndView("menu").addObject("aularios", wolService.getAularios());
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
				Ordenador o = wolService.getOrdenadorNombre(nombre_equipo);
				
				return new ModelAndView("detalle_ordenador").addObject("nombre", o.getNombre()).addObject("ip", o.getIpStr()).addObject("mac", o.getMacStr()).addObject("aulario", o.getAula().getEdificio().getNombre()).addObject("aularios", wolService.getAularios()).addObject("aula", o.getAula().getNumero()).addObject("aulas", wolService.getAulas());
			}else{
				return new ModelAndView("index_template");
			}
		}
		
	//Devuelve una lista de los ordenadores de un aula
	@RequestMapping(value="/aulario/aula", method = RequestMethod.GET)
	public ModelAndView getOrdenadorAula(@RequestParam String aulario, @RequestParam int aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			Aulario au = wolService.getAularioNombre(aulario);
			Aula aulaAux = null;
			for(Aula a : wolService.getAulasAulario(au)){
				if(a.getNumero() == aula){
					aulaAux = a;
					break;
				}
			}
			return new ModelAndView("ordenadores_aula_template").addObject("ordenadores", this.wolService.getOrdenadoresAula(aulaAux)).addObject("aula", aulaAux);
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
	
	//Modifica un ordenador
	@RequestMapping(value = "/ordenador/modificar", method = RequestMethod.POST)
	public ModelAndView modificarOrdenador(@RequestParam String nombreOri, @RequestParam String nombre, @RequestParam String ip, @RequestParam String mac, @RequestParam String aulario, @RequestParam int aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			wolService.deleteOrdenador(nombreOri);
			Aulario aularioAux = wolService.getAularioNombre(aulario);
			Aula aulaAux = null;
			for (Aula aulaIt: wolService.getAulasAulario(aularioAux)){
				if (aulaIt.getNumero() == aula){
					aulaAux = aulaIt;
					break;
				}
			}
			if (aulaAux == null){
				return new ModelAndView("actionPerformed").addObject("status", "error");
			}
			Ordenador ordenador = new Ordenador(aulaAux, ip, mac, nombre);
			if (wolService.addOrdenador(ordenador)){
				return new ModelAndView("actionPerformed").addObject("status", "exito");
			}else{
				return new ModelAndView("actionPerformed").addObject("status", "error");
			}
		}else{
			return new ModelAndView("index_template");
		}
		
	}
	
	//Formulario para añadir un aula
	@RequestMapping(value = "/aulas/addAulaForm")
	public ModelAndView addAulaForm(HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return new ModelAndView("addAulaForm_template").addObject("aularios", wolService.getAularios());
		}else{
			return new ModelAndView("index_template");
		}
		
	}
	
	//Detalles de un aula
	@RequestMapping(value = "/{aulario}/{aula}/detalles", method = RequestMethod.GET)
	public ModelAndView getDetalleAula(@PathVariable("aulario") String aulario, @PathVariable("aula") int aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			
			return new ModelAndView("detalle_aula").addObject("numero", aula).addObject("aulario", aulario).addObject("aularios", wolService.getAularios());
		}else{
			return new ModelAndView("index_template");
		}
	}
	
	
	
	
	//Devuelve una lista de las aulas de un aulario
	@RequestMapping(value="/aulario/{aulario}", method = RequestMethod.GET)
	public ModelAndView getAulasAulario(@PathVariable("aulario") String aulario, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return new ModelAndView("lista_aulas_template").addObject("aulas", this.wolService.getAularioNombre(aulario).getAulas());
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