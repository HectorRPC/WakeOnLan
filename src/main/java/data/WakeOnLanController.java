package data;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WakeOnLanController {
	
	@Autowired
	private WakeOnLanService wolService;
	
	
	//Devuelve una lista de todos los ordenadores
	@RequestMapping(value = "/ordenadores", method = RequestMethod.GET)
	public Iterable<Ordenador> getOrdenadores(HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return this.wolService.getOrdenadores();
		}else{
			return null;
		}
	}
	
	//Devuelve una lista de los ordenadores de un aula
	@RequestMapping(value="/ordenadores/{aula}", method = RequestMethod.GET)
	public Iterable<Ordenador> getOrdenadorAula(@PathVariable("aula") int aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			Aula a = this.wolService.getAulasNumero(aula).iterator().next();
			return this.wolService.getOrdenadoresAula(a);
		}else{
			return null;
		}
			
	}
	
	//Elimina un ordenador
	@RequestMapping(value = "/ordenador/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> deleteOrdenador(@RequestBody Ordenador ordenador, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			wolService.deleteOrdenador(ordenador);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}else{
			return new ResponseEntity<Boolean>(true,HttpStatus.UNAUTHORIZED);
		}
	}
	
	//Añade un ordenador
	@RequestMapping(value = "/ordenador", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addOrdenador(@RequestBody Ordenador ordenador, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			wolService.addOrdenador(ordenador);
			return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		}else{
			return new ResponseEntity<Boolean>(true,HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	//Devuelve una lista de todas las aulas
	@RequestMapping(value = "/aulas", method = RequestMethod.GET)
	public Iterable<Aula> getAulas(HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			return this.wolService.getAulas();
		}else{
			return null;
		}
	}
	
	//Devuelve una lista de las aulas de un aulario
	@RequestMapping(value="/aulas/{aulario}", method = RequestMethod.GET)
	public Iterable<Aula> getAulasAulario(@PathVariable("aulario") int aulario, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			Aulario a = this.wolService.getAulariosNumero(aulario).iterator().next();
			return this.wolService.getAulasAulario(a);
		}else{
			return null;
		}
			
	}
	
	//Elimina un aula
	@RequestMapping(value = "/aula/delete", method = RequestMethod.POST)
	public ResponseEntity<Boolean> deleteAula(@RequestBody Aula aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			wolService.deleteAula(aula);
			return new ResponseEntity<Boolean>(true,HttpStatus.OK);
		}else{
			return new ResponseEntity<Boolean>(true,HttpStatus.UNAUTHORIZED);
		}
	}
		
	//Añade un aula
	@RequestMapping(value = "/aula", method = RequestMethod.POST)
	public ResponseEntity<Boolean> addAula(@RequestBody Aula aula, HttpSession session){
		if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
			wolService.addAula(aula);
			return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
		}else{
			return new ResponseEntity<Boolean>(true,HttpStatus.UNAUTHORIZED);
		}
		
	}
	
	//Devuelve una lista de todos los aularios
		@RequestMapping(value = "/aularios", method = RequestMethod.GET)
		public Iterable<Aulario> getAularios(HttpSession session){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				return this.wolService.getAularios();
			}else{
				return null;
			}
		}
		
		//Elimina un aulario
		@RequestMapping(value = "/aulario/delete", method = RequestMethod.POST)
		public ResponseEntity<Boolean> deleteAulario(@RequestBody Aulario aulario, HttpSession session){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				wolService.deleteAulario(aulario);
				return new ResponseEntity<Boolean>(true,HttpStatus.OK);
			}else{
				return new ResponseEntity<Boolean>(true,HttpStatus.UNAUTHORIZED);
			}
		}
		
		//Añade un aulario
		@RequestMapping(value = "/aulario", method = RequestMethod.POST)
		public ResponseEntity<Boolean> addAulario(@RequestBody Aulario aulario, HttpSession session){
			if( session.getAttribute("logged")!=null && (boolean) session.getAttribute("logged")){
				wolService.addAulario(aulario);
				return new ResponseEntity<Boolean>(true,HttpStatus.CREATED);
			}else{
				return new ResponseEntity<Boolean>(true,HttpStatus.UNAUTHORIZED);
			}
		}
}