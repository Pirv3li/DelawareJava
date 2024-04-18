package domein;

public class Gebruiker {

	private String gebruikersnaam;
	private String password_Hash;
	private boolean isActief;
	private String rol;
	private int id;

	public Gebruiker(String gebruikersnaam, String password_Hash, boolean isActief, String rol,int id) {
		setGebruikersnaam(gebruikersnaam);
		setPassword_Hash(password_Hash);
		setActief(isActief);
		setRol(rol);
		setId(id);
	}

	public String getGebruikersnaam() {
		return gebruikersnaam;
	}

	private void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}

	public String getPassword_Hash() {
		return password_Hash;
	}

	private void setPassword_Hash(String password_Hash) {
		this.password_Hash = password_Hash;
	}

	public boolean isActief() {
		return isActief;
	}

	private void setActief(boolean isActief) {
		this.isActief = isActief;
	}
	
	public String getRol() {
		return rol;
	}

	private void setRol(String rol) {
		this.rol = rol;
	}
	
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

}