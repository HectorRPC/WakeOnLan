package data;

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
		userRepo.save(new User("admin", "Administrador", "admin", "admin@urjc.es", true));
		userRepo.save(new User("user", "User", "user", "user@urjc.es", false));
	}
}
