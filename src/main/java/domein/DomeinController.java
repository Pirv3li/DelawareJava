package domein;

import java.util.ArrayList;
import java.util.List;

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
	
	public List<Bestelling> FindbestellingenByLeverancierofKlant() {
		
		List<Bestelling> bestellingen =  app.FindbestellingenByLeverancierofKlant(user);
		return bestellingen;
	}
	
}