package data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
	private String alias;
	private String name;
	private String pass;
	private String mail;
	private String tlf;
	private boolean pay;
	private boolean admin;
	private String avatarfile;
	private String degree;
	public User(){
		
	}
	public User(String alias,String pass){
		this.alias = alias;
		this.pass=pass;
	}
	public User(String alias, String name, String pass, String mail,
			String tlf, boolean pay,boolean admin, String avatarfile, String degree) {
		super();
		this.alias = alias;
		this.name = name;
		this.pass = pass;
		this.mail = mail;
		this.tlf = tlf;
		this.pay = pay;
		this.admin=admin;
		this.avatarfile = avatarfile;
		this.degree = degree;
	}
	
	
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public boolean isAdmin() {
		return admin;
	}
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	public String getTlf() {
		return tlf;
	}
	public void setTlf(String tlf) {
		this.tlf = tlf;
	}
	public boolean isPay() {
		return pay;
	}
	public void setPay(boolean pay) {
		this.pay = pay;
	}
	public String getAvatarfile() {
		return avatarfile;
	}
	public void setAvatarfile(String avatarfile) {
		this.avatarfile = avatarfile;
	}
	public String getDegree() {
		return degree;
	}
	public void setDegree(String degree) {
		this.degree = degree;
	}
	
	public void setAll(User user){
		this.alias = user.alias;
		this.name = user.name;
		this.pass = user.pass;
		this.mail = user.mail;
		this.tlf = user.tlf;
		this.pay = user.pay;
		this.admin=user.admin;
		this.avatarfile = user.avatarfile;
		this.degree = user.degree;
	}
	
}
