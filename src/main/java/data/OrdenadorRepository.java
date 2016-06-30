package data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OrdenadorRepository extends CrudRepository<Ordenador,Long> {
	
	List<Ordenador> findByIpStr(String ipStr);
	List<Ordenador> findByMacStr(String macStr);
	List<Ordenador> findByNombre(String nombre);
	List<Ordenador> findByNombreContaining(String nombre);
	List<Ordenador> findAll();
	List<Ordenador> findByAula(Aula aula);
	
}