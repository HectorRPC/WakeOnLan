package data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DataBaseInit implements CommandLineRunner{
	@Autowired
	private OrdenadorRepository ordenadorRepo;
	@Autowired
	private AulaRepository aulaRepo;
	@Autowired
	private AularioRepository aularioRepo;
	
	@Autowired
	private UserRepository userRepo;
	
	@Override
	public void run(String... arg) throws Exception{
		Aulario aulario1 = new Aulario(new ArrayList<Aula>(), "Aulario 1");
		Aulario aulario2 = new Aulario(new ArrayList<Aula>(), "Aulario 2");
		Aula aula102 = new Aula(new ArrayList<Ordenador>(), aulario2, 102);
		Ordenador ordenador1 = new Ordenador(aula102, "193.147.76.150", "F8-B1-56-A7-9D-57", "ao-aul2-a102-39");
		userRepo.save(new User("admin", "Administrador", "admin", "admin@urjc.es", true));
		userRepo.save(new User("user", "User", "user", "user@urjc.es", false));
		aularioRepo.save(aulario1);
		aularioRepo.save(aulario2);
		aulaRepo.save(aula102);
		ordenadorRepo.save(ordenador1);
	}
}
