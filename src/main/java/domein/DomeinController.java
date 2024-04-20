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
	private Admin admin;

	private BedrijfDao bedrijfRepo;
	private LeverancierDao leverancierRepo;

	public DomeinController() {
		app = new App();

	};

	public void uitloggen() {
		this.leverancier = null;
		this.admin = null;
	}
	
	public boolean AanmeldenAdmin(String gebruikersnaam, String wachtwoord) {

		boolean aangemeld = false;
		this.admin = app.AanmeldenAdmin(gebruikersnaam, wachtwoord);
		if (admin != null) {
			aangemeld = true;
		}

		return aangemeld;

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



	public List<Bedrijf> getBedrijven() {
		List<Bedrijf> bedrijven = app.getBedrijven();
		return bedrijven;
	}



	public Leverancier getLeverancierGegevensByIdBedrijf(int idBedrijf) {
		Leverancier lever = app.getLeverancierGegevensByIdBedrijf(idBedrijf);
		return lever;
	}



	public Adres getAdresByIdAdres(int idAdres) {
		Adres adres = app.getAdresByIdAdres(idAdres);
		return adres;
	}

}