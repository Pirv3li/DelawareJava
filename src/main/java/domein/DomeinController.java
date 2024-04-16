package domein;

public class DomeinController {
	
	private App app;

	public DomeinController() {
		app = new App();
	};
	
	public void Aanmelden(String gebruikersnaam, String wachtwoord) {
		app.Aanmelden(gebruikersnaam, wachtwoord);
	};
	
}