package data;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface AulaRepository extends CrudRepository<Aula,Long> {
	
	List<Aula> findByNumero(int numero);
	List<Aula> findAll();
	List<Aula> findByAulario(Aulario aulario);
	Aula findByAularioAndNumero(Aulario aulario, int numero);
}
