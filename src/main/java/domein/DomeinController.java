package domein;



import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.collections.ObservableList;
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
import repository.DatabasePoller;
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
    private DatabasePoller poller;
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
	                     
	public void setPoller(DatabasePoller poller) {
		this.poller = poller;
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
	
	public ObservableList<Interface_Klant> getKlantenByLeverancierId(){
		

		return app.getKlantenByLeverancierId(leverancier.getIdLeverancier());
		
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
	
	public ObservableList<Interface_Bestelling> findBestellingenByLeverancier() {
		ObservableList<Interface_Bestelling> bestellingen = app.getBestellingenByLeverancierId(leverancier);
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

	public ObservableList<Interface_Bedrijf> getBedrijven() {
		ObservableList<Interface_Bedrijf> bedrijven = app.getBedrijven();
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
		NotificatieDaoJpa notificatieRepo = new NotificatieDaoJpa();
		
		notificatieRepo.createNotification("Gelieve te betalen!", "Betalingsherinnering", false, new Date(), bestelling);
	}
	
	public void veranderStatusOrder(String id) {
		bestellingRepo.veranderBetalingStatus(id);
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

	public void pollerList(ObservableList<Interface_Bestelling> bestellingenList) {
		poller.setCurrentList(bestellingenList);
		
	}
}