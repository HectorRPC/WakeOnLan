package data;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Aula {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany(mappedBy="aula")	
    private ArrayList<Ordenador> ordenadores;

	@ManyToOne
	private Aulario aulario;
	
	private int numero;
	
	//Constructores
    public Aula() {
    }

    public Aula(ArrayList<Ordenador> ordenadores, Aulario aulario, int numero) {
		super();
		this.ordenadores = ordenadores;
		this.aulario = aulario;
		this.numero = numero;
	}

	//Getters & Setters
    public ArrayList<Ordenador> getOrdenadores() {
        return ordenadores;
    }

    public void setOrdenadores(ArrayList<Ordenador> ordenadores) {
        this.ordenadores = ordenadores;
    }
    
    public Aulario getAulario() {
		return aulario;
	}

	public void setAulario(Aulario aulario) {
		this.aulario = aulario;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	//Añade el ordenador deseado al aula.
    public void addOrdenador(Ordenador ordenador){
        this.ordenadores.add(ordenador);
    }
    
    //Elimina el ordenador deseado del aula.
    public void removeOrdenador(Ordenador ordenador){
        this.ordenadores.remove(this.ordenadores.indexOf(ordenador));
    }
    
    //Enciende todos los ordenadores de un aula.
    public boolean wakeAula(){
        boolean b = true;
        for(Ordenador ordenador: this.ordenadores){
            b &= ordenador.wakeOrdenador();
        }
        return b;
    }
}
