package data;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Aulario {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@OneToMany(mappedBy="aulario")
	private ArrayList<Aula> aulas;
	
	private int numero;

	//Constructores
    public Aulario() {
    }

    public Aulario(ArrayList<Aula> aulas, int numero) {
        this.aulas = aulas;
        this.numero = numero;
    }

    //Getters & Setters
    public ArrayList<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(ArrayList<Aula> aulas) {
        this.aulas = aulas;
    }

    public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

	
	//Añade el aula deseado al aulario.
    public void addAula(Aula aula){
        this.aulas.add(aula);
    }
    
    //Elimina el aula deseado del aulario.
    public void removeAula(Aula aula){
        this.aulas.remove(this.aulas.indexOf(aula));
    }
    
    //Enciende todos los ordenadores de un aulario.
    public boolean wakeAulario(){
        boolean b = true;
        for(Aula aula: this.aulas){
            b &= aula.wakeAula();
        }
        return b;
    }
}
