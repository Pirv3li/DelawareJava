package domein;




import java.util.Date;
import javafx.collections.ObservableList;
import repository.AdminDao;
import repository.AdminDaoJpa;
import repository.AdresDao;
import repository.AdresDaoJpa;
import repository.LeverancierDao;
import repository.LeverancierDaoJpa;


public class DomeinController {

	private B2B_Portal app;
	private Leverancier leverancier;
	private Admin admin;


    private LeverancierDao leverancierRepo;
    private AdresDao adresRepo;
    private AdminDao adminRepo;



	 public DomeinController() {
	        this.leverancierRepo = new LeverancierDaoJpa();
	        this.adresRepo = new AdresDaoJpa();
	        this.adminRepo = new AdminDaoJpa();
	        this.app = new B2B_Portal(leverancierRepo, adresRepo, adminRepo);
}
	                     
	public void uitloggen() {
		setLeverancier(null);
		setAdmin(null);
	}

	public boolean aanmeldenAdmin(String gebruikersnaam, String wachtwoord) {
		boolean aangemeld = false;
		setAdmin(app.aanmeldenAdmin(gebruikersnaam, wachtwoord));
		if (admin != null) {
			aangemeld = true;
		}

		return aangemeld;
	};


	public boolean aanmelden(String gebruikersnaam, String wachtwoord) {
		boolean aangemeld = false;
		setLeverancier(app.aanmelden(gebruikersnaam, wachtwoord));
		if (leverancier != null) {
			aangemeld = true;
		}

		return aangemeld;
	};
	
	public ObservableList<Interface_Klant> getKlantenByLeverancierId(int aantal, int begin){
		

		return app.getKlantenByLeverancierId(leverancier.getIdLeverancier(), aantal, begin);
	}
	
	
	
	public Interface_Klant getKlantById(int klantId) {
		return app.getKlantById(klantId);
	}
	
	public ObservableList<Interface_Bestelling> findBestellingenByKlant(Interface_Klant klant) {
		ObservableList<Interface_Bestelling> bestellingen = app.getBestellingenByKlantId(klant);
		return bestellingen;
	}
	
	public void setAantalBestellingen(Interface_Klant klant) {
		app.setAantalBestellingenByKlant(klant);
	}
	
	public ObservableList<Interface_Bestelling> findBestellingenByLeverancier(int aantal, int begin) {
		ObservableList<Interface_Bestelling> bestellingen = app.getBestellingenByLeverancierId(leverancier, aantal, begin);
		return bestellingen;
	}

	public ObservableList<Interface_BestellingDetails> getBestellingDetails(Interface_Bestelling bestelling) {
		ObservableList<Interface_BestellingDetails> bestellingDetails = app.getBestellingDetails(bestelling);
		return bestellingDetails;
	}

	public Interface_Product getProductByProductId(Interface_BestellingDetails bestellingDetail) {
		Interface_Product product = app.getProductByProductId(bestellingDetail.getIdProduct());
		return product;
	}

	public ObservableList<Interface_Bedrijf> getBedrijven(int aantal, int begin) {
		ObservableList<Interface_Bedrijf> bedrijven = app.getBedrijven(aantal, begin);
		return bedrijven;
	}
	
	public Interface_Bedrijf getBedrijfByKlant(Interface_Klant klant) {
		return app.getBedrijfByKlantId(klant.getIdKlant());
	}

	public Interface_Leverancier getLeverancierGegevensByIdBedrijf(int idBedrijf) {
		Interface_Leverancier lever = app.getLeverancierGegevensByIdBedrijf(idBedrijf);
		return lever;
	}
	

	public Interface_Adres getAdresByIdAdres(int idAdres) {
		Interface_Adres adres = app.getAdresByIdAdres(idAdres);
		return adres;
	}
	
	public void maakNotificatie(Interface_Bestelling bestelling) {
		app.createNotificatie("Gelieve te betalen!", "Betalingsherinnering", false, new Date(), bestelling);
	}
	
	
	public void veranderStatusOrder(String id) {
		app.veranderBetalingStatus(id);
	}

	public ObservableList<Interface_GoedKeuringLeverancier> getGoedKeuringen(String Soort, int aantal, int begin){

		return app.getAllByStatusAfhandeling(Soort, aantal, begin);
	}
	
	public Interface_Leverancier getLeverancierById(int idLeverancier) {
		
		return app.getLeverancierById(idLeverancier);
	}
	
	public void updateGoedkeuringLeverancier(int id, String afgehandeld) {
		app.keuringVeranderVerzoekenLeverancier(id, afgehandeld);
	}
	
	public void updateLeverancierById(int idLeverancier, String gebruikersnaam, String email, String iban, String btwNummer, String telefoonnummer,
			String sector, String straat, String nummer, String stad,String postcode) {
		app.updateLeverancierById(idLeverancier, gebruikersnaam, email, iban, btwNummer, telefoonnummer, sector, straat, nummer, stad, postcode);
	}
	
	//getters en setters

	public void setApp(B2B_Portal app) {
		this.app = app;
	}

	public Interface_Leverancier getLeverancier() {
		return leverancier;
	}

	public void setLeverancier(Leverancier leverancier) {
		this.leverancier = leverancier;
	}
	
	private void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void updateLeverancier(Interface_Leverancier lever) {
		app.updateLeverancier(lever);
	}

}