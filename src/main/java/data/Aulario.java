package data;

import java.util.ArrayList;
import java.util.List;

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
	private List<Aula> aulas;
	
	private String nombre;

	//Constructores
    public Aulario() {
    }

    public Aulario(List<Aula> aulas, String nombre) {
        this.aulas = aulas;
        this.nombre = nombre;
    }

    //Getters & Setters
    public List<Aula> getAulas() {
        return aulas;
    }

    public void setAulas(List<Aula> aulas) {
        this.aulas = aulas;
    }

    public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
