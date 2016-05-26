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
	
	public Iterable<Ordenador> getOrdenadores(){
		return ordenadorRepo.findAll();
	}
	
	public Iterable<Ordenador> getOrdenadoresIP(String ipStr){
		return ordenadorRepo.findByIpStr(ipStr);
	}
	
	public Iterable<Ordenador> getOrdenadoresMAC(String macStr){
		return ordenadorRepo.findByMacStr(macStr);
	}
	
	public Iterable<Ordenador> getOrdenadoresNombre(String nombre_equipo){
		return ordenadorRepo.findByNombre_equipoContaining(nombre_equipo);
	}
	
	public Iterable<Ordenador> getOrdenadoresAula(Aula aula){
		return ordenadorRepo.findByAula(aula);
	}
	
	public Iterable<Aula> getAulasNumero(int numero){
		return aulaRepo.findByNumero(numero);
	}
	
	public Iterable<Aula> getAulasAulario(Aulario aulario){
		return aulaRepo.findByAulario(aulario);
	}
	
	public Iterable<Aulario> getAularios(){
		return aularioRepo.findAll();
	}
	
	public Iterable<Aulario> getAulariosNumero(int numero){
		return aularioRepo.findByNumero(numero);
	}
	
	public boolean addOrdenador(Ordenador ordenador){
		try{
			ordenadorRepo.save(ordenador);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public boolean addAula(Aula aula){
		try{
			aulaRepo.save(aula);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public boolean addAulario(Aulario aulario){
		try{
			aularioRepo.save(aulario);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	public boolean deleteOrdenador(Ordenador ordenador){
		try{
			ordenadorRepo.delete(ordenador);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	public boolean deleteAula(Aula aula){
		try{
			aulaRepo.delete(aula);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	public boolean deleteAulario(Aulario aulario){
		try{
			aularioRepo.delete(aulario);
		}catch(Exception e){
			return false;
		}
		return true;
	}
	
	
}
