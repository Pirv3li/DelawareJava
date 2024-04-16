package domein;

public class Gebruiker {

	private String gebruikersnaam;
	private String password_Hash;
	private boolean isActief;

	public Gebruiker(String gebruikersnaam, String password_Hash, boolean isActief) {
		setGebruikersnaam(gebruikersnaam);
		setPassword_Hash(password_Hash);
		setActief(isActief);
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

}