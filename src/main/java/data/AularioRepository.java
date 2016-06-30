package data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AularioRepository extends CrudRepository<Aulario,Long> {
	
	Aulario findByNumero(int numero);
	List<Aulario> findAll();
}
