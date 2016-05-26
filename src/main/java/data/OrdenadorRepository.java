package data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface OrdenadorRepository extends CrudRepository<Ordenador,Long> {
	
	List<Ordenador> findByIpStr(String ipStr);
	List<Ordenador> findByMacStr(String macStr);
	List<Ordenador> findByNombre_equipo(String nombre_equipo);
	List<Ordenador> findByNombre_equipoContaining(String nombre_equipo);
	List<Ordenador> findAll();
	List<Ordenador> findByAula(Aula aula);
	
}