package domein;

public class DomeinController {
	
	private App app;

	public DomeinController() {
		app = new App();
	};
	
	public String Aanmelden(String gebruikersnaam, String wachtwoord) {
		return app.Aanmelden(gebruikersnaam, wachtwoord);
	};
	
}