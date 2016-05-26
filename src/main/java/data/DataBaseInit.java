package data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Controller;

@Controller
public class DataBaseInit implements CommandLineRunner{
	@Autowired
	private PostImageRepository imageRepo;
	@Autowired
	private PostMusicRepository musicRepo;
	@Autowired
	private PostTextRepository textRepo;
	@Autowired
	private PostVideoRepository videoRepo;
	@Autowired
	private UserRepository userRepo;
	@Autowired
	private PrivateMsgRepository msgRepo;
	
	@Override
	public void run(String... arg) throws Exception{
		
		textRepo.save(new PostText("Reiner","Gatitos","El gato o gato doméstico (Felis silvestris catus) y coloquialmente llamado minino,1 micho,2 mizo3 o miz;"));
		textRepo.save(new PostText("gon","Odio a los gatos","Adoro todos los animales pero hay uno que no soporto y que incluso odio: los gatos."));
		
		userRepo.save(new User("gon","Gonzalo Ruanes Gil","miau","gon@lcdd.com","666666666",true,true,"https://raverkid.files.wordpress.com/2012/10/illuminati.jpg","GII+GIS"));
		userRepo.save(new User("Reiner","Victor Muñoz Reyes","reiner","reiner@lcdd.com","6358954168",false,false,"http://vignette3.wikia.nocookie.net/es.futurama/images/d/dd/681754fry3-full.jpg/revision/latest?cb=20110606042458","GIS"));
		userRepo.save(new User("Hector","Ruiz-Poveda Coca","hector","hector@lcdd.com","635214987",true,false,"http://i.ytimg.com/vi/TIrArYt6Lj8/hqdefault.jpg","GII+GIS"));
		musicRepo.save(new PostMusic("Reiner","Keyboard cat","8ec711a","Ejemplo de lo buenos que son los gatos para nuestra sociedad. Son muy monos :3"));
		musicRepo.save(new PostMusic("gon","Viejoven - Ojete Calor","d73179f","Menuda canción, es lo más sublime que he encontrado en mucho tiempo"));
		videoRepo.save(new PostVideo("Reiner","Como molan las tortugas","eD0PMKTR3nU","No solo los gatos, también las tortugas tienen hueco en este blog."));
		videoRepo.save(new PostVideo("Hector","¡Ese Mica como mola se merece una ola!","Y_fhPbGYzHQ","Porque es un profe excelente, porque es un profe excelente, porque es un profe excelente, y siempre lo sera :)"));
		videoRepo.save(new PostVideo("Reiner","Esta practica es: ","StTqXEQ2l-Y","Todo es increible :)"));
		videoRepo.save(new PostVideo("gon","Arriba Vicentin!!!! ","TIrArYt6Lj8","Porque la vida tiene forma de cancion."));
		imageRepo.save(new PostImage("Hector","Gatito","https://mepillasenbragas.files.wordpress.com/2009/10/gatitovaamorir82659071.jpg","Esta es una foto de un gatito :D"));
		imageRepo.save(new PostImage("gon","Este gatito mola mas","http://static.hogarutil.com/archivos/201204/estrenimiento-gato-bebe2-xl-668x400x80xX.jpg","Ese no esta mal, pero el mio mola mas."));
		imageRepo.save(new PostImage("Reiner","¡¡¡GATETAS!!!","http://elsensacional.infonews.com/files/image/26/26630/5049fe491edf7_513_!.jpg?s=d98776da761dfbda06255e2555700cd4","La combinacion definitiva."));
		imageRepo.save(new PostImage("Reiner","David Hasselhoff aprueba esta Practica","http://rwrant.co.za/wp-content/uploads/2013/11/David-Hasselhoff.jpg","Perfect Post!"));
		msgRepo.save(new PrivateMsg("gon","Hola","Encantado de conocerte.","Reiner"));
		msgRepo.save(new PrivateMsg("Reiner","RE: Hola","El gusto es mio.","gon"));
	}
}
