package domein;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import repository.AdminDao;
import repository.AdminDaoJpa;
import repository.AdresDao;
import repository.AdresDaoJpa;
import repository.BedrijfDao;
import repository.BedrijfDaoJpa;
import repository.BestellingDao;
import repository.BestellingDaoJpa;
import repository.BestellingDetailsDao;
import repository.BestellingDetailsDaoJpa;
import repository.KlantDao;
import repository.KlantDaoJpa;
import repository.LeverancierDao;
import repository.LeverancierDaoJpa;
import repository.NotificatieDaoJpa;
import repository.ProductDao;
import repository.ProductDaoJpa;

public class DomeinController {

	private B2B_Portal app;
	private Leverancier leverancier;
	private Admin admin;

	private BedrijfDao bedrijfRepo;
    private LeverancierDao leverancierRepo;
    private BestellingDao bestellingRepo;
    private BestellingDetailsDao bestellingDetailsRepo;
    private ProductDao productRepo;
    private AdresDao adresRepo;
    private AdminDao adminRepo;
    private KlantDao klantRepo;

	 public DomeinController() {
	        this.bedrijfRepo = new BedrijfDaoJpa();
	        this.leverancierRepo = new LeverancierDaoJpa();
	        this.bestellingRepo = new BestellingDaoJpa();
	        this.bestellingDetailsRepo = new BestellingDetailsDaoJpa();
	        this.productRepo = new ProductDaoJpa();
	        this.adresRepo = new AdresDaoJpa();
	        this.adminRepo = new AdminDaoJpa();
	        this.klantRepo = new KlantDaoJpa();
	        
	        this.app = new B2B_Portal(bedrijfRepo, leverancierRepo, bestellingRepo, bestellingDetailsRepo, productRepo,
                    adresRepo, adminRepo, klantRepo);
}
	                     

	public void uitloggen() {
		this.leverancier = null;
		this.admin = null;
	}

	public boolean aanmeldenAdmin(String gebruikersnaam, String wachtwoord) {
		boolean aangemeld = false;
		this.admin = app.aanmeldenAdmin(gebruikersnaam, wachtwoord);
		if (admin != null) {
			aangemeld = true;
		}

		return aangemeld;
	};

	public boolean aanmelden(String gebruikersnaam, String wachtwoord) {
		boolean aangemeld = false;
		this.leverancier = app.aanmelden(gebruikersnaam, wachtwoord);
		if (leverancier != null) {
			aangemeld = true;
		}

		return aangemeld;
	};
	
	public List<Klant> getKlantenByLeverancierId(){
		return app.getKlantenByLeverancierId(leverancier.getIdLeverancier());
	}
	
	public Klant getKlantById(int klantId) {
		return app.getKlantById(klantId);
	}
	
	public List<Bestelling> findBestellingenByKlant(Klant klant) {
		List<Bestelling> bestellingen = app.getBestellingenByKlantId(klant);
		return bestellingen;
	}
	
	public void setAantalBestellingen(Klant klant) {
		app.setAantalBestellingenByKlant(klant);
	}
	
	public List<Bestelling> findBestellingenByLeverancier() {
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
	
	public Bedrijf getBedrijfByKlant(Klant klant) {
		return app.getBedrijfByKlantId(klant.getIdKlant());
	}

	public Adres getAdresByIdAdres(int idAdres) {
		Adres adres = app.getAdresByIdAdres(idAdres);
		return adres;
	}
	
	public void maakNotificatie(Bestelling bestelling) {
		NotificatieDaoJpa notificatieRepo = new NotificatieDaoJpa();
		
		notificatieRepo.createNotification("Gelieve te betalen!", "Betalingsherinnering", false, new Date(), bestelling);
	}
	
	public void veranderStatusOrder(String id, boolean Status) {
		BestellingDaoJpa bestellingRepo = new BestellingDaoJpa();
		
		bestellingRepo.veranderBetalingStatus(id, Status);
	}

	//getters en setters

	public void setApp(B2B_Portal app) {
		this.app = app;
	}

	public Leverancier getLeverancier() {
		return leverancier;
	}

	public void setLeverancier(Leverancier leverancier) {
		this.leverancier = leverancier;
	}

	public Admin getAdmin() {
		return admin;
	}

	public void updateLeverancier(Leverancier lever) {
		app.updateLeverancier(lever);
	}
}