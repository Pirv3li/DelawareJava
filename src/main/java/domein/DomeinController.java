package domein;

public class DomeinController {
	
	private App app;
	private Gebruiker user;

	public DomeinController() {
		app = new App();
	};
	
	public boolean Aanmelden(String gebruikersnaam, String wachtwoord) {
		boolean aangemeld = false;
		this.user = app.Aanmelden(gebruikersnaam, wachtwoord);
		if(user != null) {
			aangemeld = true;
		}
		return aangemeld;
	};
	
}