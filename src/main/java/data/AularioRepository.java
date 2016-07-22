package data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AularioRepository extends CrudRepository<Aulario,Long> {
	
	Aulario findByNombre(String nombre);
	List<Aulario> findByNombreContaining(String nombre);
	List<Aulario> findAll();
}
