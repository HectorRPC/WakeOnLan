package data;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Ordenador {
    
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	@ManyToOne
	private Aula aula;
	
    private String ipStr;
    private String macStr;
    private String nombre;
    public static final int PORT = 9;

    //Constructores
    public Ordenador() {
    	
	}

    public Ordenador(Aula aula, String ipStr, String macStr, String nombre) {
		super();
		this.aula = aula;
		this.ipStr = ipStr;
		this.macStr = macStr;
		this.nombre = nombre;
	}

	//Getters & Setters
    public String getIpStr() {
        return ipStr;
    }

    public void setIpStr(String ipStr) {
        this.ipStr = ipStr;
    }

    public String getMacStr() {
        return macStr;
    }

    public void setMacStr(String macStr) {
        this.macStr = macStr;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
    public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	//Metodo que manda un paquete a la IP-MAC seleccionada para arrancar dicho ordenador.
    public boolean wakeOrdenador() {
        
        try {
            byte[] macBytes = getMacBytes(macStr);
            byte[] bytes = new byte[6 + 16 * macBytes.length];
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) 0xff;
            }
            for (int i = 6; i < bytes.length; i += macBytes.length) {
                System.arraycopy(macBytes, 0, bytes, i, macBytes.length);
            }
            
            InetAddress address = InetAddress.getByName(ipStr);
            DatagramPacket packet = new DatagramPacket(bytes, bytes.length, address, PORT);
            DatagramSocket socket = new DatagramSocket();
            socket.send(packet);
            socket.close();
            
            System.out.println("Paquete Wake-on-LAN enviado.");
        }
        catch (Exception e) {
            System.out.println("Error al enviar pquete Wake-on-LAN: + e");
            return false;
        }
        return true;
    }
    
    //Metodo utilizado por wakePC. Transforma la dirección MAC en bytes
    private static byte[] getMacBytes(String macStr) throws IllegalArgumentException {
        byte[] bytes = new byte[6];
        String[] hex = macStr.split("(\\:|\\-)");
        if (hex.length != 6) {
            throw new IllegalArgumentException("Direccion MAC invalida.");
        }
        try {
            for (int i = 0; i < 6; i++) {
                bytes[i] = (byte) Integer.parseInt(hex[i], 16);
            }
        }
        catch (NumberFormatException e) {
            throw new IllegalArgumentException("Digito hexadecimal invalido en la direccion MAC.");
        }
        return bytes;
    }
}
