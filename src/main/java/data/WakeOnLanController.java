package data;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class WakeOnLanController {
	
	@Autowired
	private WakeOnLanService wolService;
	
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
				Ordenador o = this.wolService.getOrdenadorNombre(nombre_equipo);
				Aula a = o.getAula();
				Aulario au = a.getEdificio();
				return new ModelAndView("detalle_ordenador").addObject("nombre", o.getNombre()).addObject("mac", o.getMacStr()).addObject("ip", o.getIpStr()).addObject("aulario", au.getNombre()).addObject("aularios", wolService.getAularios()).addObject("aula", a.getNumero()).addObject("aulas", wolService.getAulasAulario(au));
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
	public ModelAndView addOrdenador(@RequestParam String nombre, @RequestParam String ip, @RequestParam String mac, @RequestParam String aulario, @RequestParam int aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			Ordenador ord = wolService.getOrdenadorNombre(nombre);
			if(ord != null){
				return new ModelAndView("actionPerformed").addObject("status", "error");
			}
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
		
	//Devuelve una lista de los ordenadores de un aula
		@RequestMapping(value="/aulario/aula", method = RequestMethod.GET)
	public ModelAndView getOrdenadorAula(@RequestParam String aulario, @RequestParam int aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			Aulario au = wolService.getAularioNombre(aulario);
			Aula aulaAux = null;
			for (Aula a: au.getAulas()){
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
		
	//Detalles de un aula
	@RequestMapping(value = "/{aulario}/{aula}/detalles")
	public ModelAndView getDetalleAula(HttpSession session, @PathVariable("aulario") String aulario, @PathVariable("aula") int aula){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				Aulario au = wolService.getAularioNombre(aulario);
				Aula aulaAux = wolService.getAulasAularioAndNumero(au, aula);
				return new ModelAndView("detalle_aula").addObject("numero", aulaAux.getNumero()).addObject("aulario", aulaAux.getEdificio().getNombre()).addObject("aularios", wolService.getAularios());
			}else{
				return new ModelAndView("index_template");
			}
		}
		
	//Modifica un aula
	@RequestMapping(value = "/aula/modificar", method = RequestMethod.POST)
	public ModelAndView modificarAula(@RequestParam int numeroOri, @RequestParam int numero, @RequestParam String aulario, @RequestParam String aularioOri, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			
			Aulario auOri = wolService.getAularioNombre(aularioOri);
			Aulario auNuevo = wolService.getAularioNombre(aulario);
			Aula aulaOri = wolService.getAulasAularioAndNumero(auOri, numeroOri);
			Aula aulaNueva = null;
			aulaNueva = wolService.getAulasAularioAndNumero(auNuevo, numero);
			if (aulaNueva != null){
				return new ModelAndView("actionPerformed").addObject("status", "error");
			}
			aulaOri.setAulario(auNuevo);
			aulaOri.setNumero(numero);
			wolService.addAula(aulaOri);
			return new ModelAndView("actionPerformed").addObject("status", "exito");
		}else{
			return new ModelAndView("index_template");
		}
		
	}
		
	//Devuelve una lista de todas las aulas
	@RequestMapping(value = "/aulas", method = RequestMethod.GET)
	public ModelAndView getAulas(HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return new ModelAndView("lista_aulas_template").addObject("aulas", this.wolService.getAulas());
		}else{
			return new ModelAndView("index_template");
		}
	}
	
	//Elimina un aula
	@RequestMapping(value = "/aula/delete", method = RequestMethod.POST)
	public ModelAndView deleteAula(@RequestParam String aulario, @RequestParam int aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			Aulario au = wolService.getAularioNombre(aulario);
			Aula a = wolService.getAulasAularioAndNumero(au, aula);
			if (wolService.deleteAula(a)){
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
			
	//Añade un aula
	@RequestMapping(value = "/aula", method = RequestMethod.POST)
	public ModelAndView addAula(@RequestParam String aulario, @RequestParam int aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			Aulario au = wolService.getAularioNombre(aulario);
			Aula aulaAux = new Aula(new ArrayList<Ordenador>(), au, aula);
			if (wolService.addAula(aulaAux)){
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
			return new ModelAndView("lista_aularios_template").addObject("aularios", this.wolService.getAularios());
		}else{
			return new ModelAndView("index_template");
		}
	}
			
	//Devuelve una lista de todas las aulas de un aulario
		@RequestMapping(value = "/aulario/lista", method = RequestMethod.GET)
	public ModelAndView getAulasAulario(@RequestParam String aulario, HttpSession session){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				Aulario au = wolService.getAularioNombre(aulario);
				return new ModelAndView("aulas_aulario_template").addObject("aulas", this.wolService.getAulasAulario(au)).addObject("aulario", aulario);
			}else{
				return new ModelAndView("index_template");
			}
		}
			
	//Formulario para añadir un aulario
	@RequestMapping(value = "/aularios/addAularioForm")
	public ModelAndView addAularioForm(HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return new ModelAndView("addAularioForm_template");
		}else{
			return new ModelAndView("index_template");
		}
		
	}
		
	//Detalles de un aulario
	@RequestMapping(value = "/{aulario}/detalles")
	public ModelAndView getDetalleAulario(HttpSession session, @PathVariable("aulario") String aulario){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				Aulario au = wolService.getAularioNombre(aulario);
				return new ModelAndView("detalle_aulario").addObject("aulario", au.getNombre());
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
		public ModelAndView addAulario(@RequestParam String aulario, HttpSession session){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				Aulario au = new Aulario(new ArrayList<Aula>(), aulario);
				if (wolService.addAulario(au)){
					return new ModelAndView("actionPerformed").addObject("status", "exito");
				}else{
					return new ModelAndView("actionPerformed").addObject("status", "error");
				}
			}else{
				return new ModelAndView("index_template");
			}
			
		}
		
}