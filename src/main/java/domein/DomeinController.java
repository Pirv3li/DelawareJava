package domein;

import java.util.ArrayList;
import java.util.List;

import repository.BedrijfDao;
import repository.BedrijfDaoJpa;

import repository.GenericDaoJpa;
import repository.LeverancierDao;
import repository.LeverancierDaoJpa;

public class DomeinController {

	private App app;
	private Leverancier leverancier;

	private BedrijfDao bedrijfRepo;
	private LeverancierDao leverancierRepo;

	public DomeinController() {
		app = new App();

	};



	public boolean Aanmelden(String gebruikersnaam, String wachtwoord) {

		boolean aangemeld = false;
		this.leverancier = app.Aanmelden(gebruikersnaam, wachtwoord);
		if (leverancier != null) {
			aangemeld = true;
		}

		return aangemeld;

	};

	public List<Bestelling> FindbestellingenByLeverancier() {

		List<Bestelling> bestellingen = app.getBestellingenByLeverancierId(leverancier);
		return bestellingen;
	}

	public List<BestellingDetails> getBestellingDetails(Bestelling bestelling) {

		List<BestellingDetails> bestellingDetails = app.getBestellingDetails(bestelling);
		return bestellingDetails;
	}
	
	public Product getProductByProductId(BestellingDetails bestellingDetail) {

		Product product = app.getProductByProductId(bestellingDetail.getIdProduct());
		return product;
	}

}