package domein;

import java.io.Serializable;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@MappedSuperclass
public class Gebruiker implements Serializable {

	private static final long serialVersionUID = 1L;

	private String gebruikersnaam;
	private String password_Hash;

	public Gebruiker(String gebruikersnaam, String password_Hash) {
		setGebruikersnaam(gebruikersnaam);
		setPassword_Hash(password_Hash);
	}

	public Gebruiker() {
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

}