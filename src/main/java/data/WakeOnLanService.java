package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WakeOnLanService {
	
	@Autowired
	private OrdenadorRepository ordenadorRepo;
	@Autowired
	private AulaRepository aulaRepo;
	@Autowired
	private AularioRepository aularioRepo;
	
	//Devuelve todos los ordenadores
	public Iterable<Ordenador> getOrdenadores(){
		return ordenadorRepo.findAll();
	}
	
	//Devuelve todos los ordenadores con una IP determinada
	public Iterable<Ordenador> getOrdenadoresIP(String ipStr){
		return ordenadorRepo.findByIpStr(ipStr);
	}
	
	//Devuelve todos los ordenadores con una direccion MAC determinada
	public Iterable<Ordenador> getOrdenadoresMAC(String macStr){
		return ordenadorRepo.findByMacStr(macStr);
	}
	
	//Devuelve todos los ordenadores que contienen el Sring especificado en su nombre de equipo
	public Iterable<Ordenador> getOrdenadoresNombre(String nombre_equipo){
		return ordenadorRepo.findByNombre_equipoContaining(nombre_equipo);
	}
	
	//Devuelve todos los ordenadores de un aula determinada
	public Iterable<Ordenador> getOrdenadoresAula(Aula aula){
		return ordenadorRepo.findByAula(aula);
	}
	
	//Devuelve todas las aulas
		public Iterable<Aula> getAulas(){
			return aulaRepo.findAll();
		}
	
	//Devuelve todas las aulas con un determinado numero
	public Iterable<Aula> getAulasNumero(int numero){
		return aulaRepo.findByNumero(numero);
	}
	
	//Devuelve todas las aulas de un determinado aulario
	public Iterable<Aula> getAulasAulario(Aulario aulario){
		return aulaRepo.findByAulario(aulario);
	}
	
	//Devuelve todos los aularios
	public Iterable<Aulario> getAularios(){
		return aularioRepo.findAll();
	}
	
	//Devuelve todos los aularios con un determinado numero
	public Iterable<Aulario> getAulariosNumero(int numero){
		return aularioRepo.findByNumero(numero);
	}
	
	//Añade un ordenador al repositorio
	public boolean addOrdenador(Ordenador ordenador){
		try{
			ordenadorRepo.save(ordenador);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	//Añade un aula al repositorio
	public boolean addAula(Aula aula){
		try{
			aulaRepo.save(aula);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	//Añade un aulario al repositorio
	public boolean addAulario(Aulario aulario){
		try{
			aularioRepo.save(aulario);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	//Elimina un ordenador del repositorio
	public boolean deleteOrdenador(Ordenador ordenador){
		try{
			ordenadorRepo.delete(ordenador);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	//Elimina un aula del repositorio
	public boolean deleteAula(Aula aula){
		try{
			aulaRepo.delete(aula);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	//Elimina un aulario del repositorio
	public boolean deleteAulario(Aulario aulario){
		try{
			aularioRepo.delete(aulario);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
}
