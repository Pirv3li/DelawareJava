package domein;

import repository.KlantDao;
import repository.AdminDao;
import repository.AdresDao;
import repository.BedrijfDao;
import repository.BestellingDao;
import repository.BestellingDetailsDao;
import repository.GoedKeuringLeverancierDao;
import repository.LeverancierDao;
import repository.ProductDao;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class B2B_Portal {

	private static final int ARGON_ITERATIONS = 6;
	private static final int ARGON_MEMORY = 1 << 17;

	private BedrijfDao bedrijfRepo;
    private LeverancierDao leverancierRepo;
    private BestellingDao bestellingRepo;
    private BestellingDetailsDao bestellingDetailsRepo;
    private ProductDao productRepo;
    private AdresDao adresRepo;
    private AdminDao adminRepo;
    private KlantDao klantRepo;
    private GoedKeuringLeverancierDao goedKeuringLeverancieRepo;

    public B2B_Portal(BedrijfDao bedrijfRepo, LeverancierDao leverancierRepo, BestellingDao bestellingRepo,
                      BestellingDetailsDao bestellingDetailsRepo, ProductDao productRepo, AdresDao adresRepo,
                      AdminDao adminRepo, KlantDao klantRepo, GoedKeuringLeverancierDao goedKeuringLeverancieRepo) {
        this.bedrijfRepo = bedrijfRepo;
        this.leverancierRepo = leverancierRepo;
        this.bestellingRepo = bestellingRepo;
        this.bestellingDetailsRepo = bestellingDetailsRepo;
        this.productRepo = productRepo;
        this.adresRepo = adresRepo;
        this.adminRepo = adminRepo;
        this.klantRepo = klantRepo;
        this.goedKeuringLeverancieRepo = goedKeuringLeverancieRepo;
    }
	public Admin aanmeldenAdmin(String gebruikersnaam, String password) {
		try {
			Admin admin = adminRepo.getAdminByGebruikersnaam(gebruikersnaam);
			if (admin == null || !verifyPassword(password, admin.getPassword_Hash())) {
				return null;
			}
			return admin;
		}
		catch(Exception e){
			return null;

		}

	}

	public Leverancier aanmelden(String gebruikersnaam, String password) {
		try {
			Leverancier leverancier = leverancierRepo.getLeverancierByGebruikersnaam(gebruikersnaam);
			if (leverancier != null && verifyPassword(password, leverancier.getPassword_Hash())) {
				return leverancier;
			}
			return null;
		}
		//catching alles veranderen naar andere exception
		catch (Exception e){
			return null;
		}

	}
	
	public ObservableList<Interface_Klant> getKlantenByLeverancierId(int idLeverancier){
		ObservableList<Interface_Klant> klanten = FXCollections.observableArrayList(klantRepo.getKlantenByLeverancierID(idLeverancier));
		
		for (Interface_Klant klant : klanten) {
			setAantalBestellingenByKlant(klant);
		}
		
		return klanten;
	}
	
	public Interface_Klant getKlantById(int idKlant){
		return klantRepo.getKlantById(idKlant);
	}

	public ObservableList<Interface_Bestelling> getBestellingenByLeverancierId(Interface_Leverancier user) {
		return FXCollections.observableArrayList(bestellingRepo.getBestellingenByLeverancierId(user.getIdLeverancier()));
	}

	public ObservableList<Interface_Bestelling> getBestellingenByKlantId(Interface_Klant user) {
		return FXCollections.observableArrayList(bestellingRepo.getBestellingenByKlantId(user.getIdKlant()));
	}
	
	public void setAantalBestellingenByKlant(Interface_Klant klant) {
		List<Bestelling> bestellingen = bestellingRepo.getBestellingenByKlantId(klant.getIdKlant());
		int countUnpaidOrders = 0;
		int countAllorders = 0;

		for (Interface_Bestelling bestelling : bestellingen) {
			countAllorders ++;
		    if (bestelling.getBetalingStatus()=="Niet betaald" || bestelling.getOrderStatus().contains("niet-verzonden")) {
		        countUnpaidOrders++;
		    }
		}
		klant.setAantalBestellingen(countUnpaidOrders);
		klant.setTotaalBestellingen(countAllorders);
	}
	
	public ObservableList<Interface_BestellingDetails> getBestellingDetails(Interface_Bestelling bestelling) {
		ObservableList<Interface_BestellingDetails> bestellingDetails = FXCollections.observableArrayList(bestellingDetailsRepo
				.getBestellingDetailsByOrderId(String.valueOf(bestelling.getIdOrder())));
		return bestellingDetails;
	}

	public Interface_Product getProductByProductId(int id) {
		return productRepo.getProductByProductId(id);
	}

	private boolean verifyPassword(String password, String hashedPassword) {
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, ARGON_ITERATIONS, ARGON_MEMORY);

		try {
			return argon2.verify(hashedPassword, password.toCharArray(), StandardCharsets.UTF_8);
		} catch (Exception e) {
//			System.out.println("Error verifying password: " + e.getMessage());
			return false;
		}
	}

	public ObservableList<Interface_Bedrijf> getBedrijven() {
		return FXCollections.observableArrayList(bedrijfRepo.getBedrijven());
	}
	
	public Interface_Bedrijf getBedrijfByKlantId(int idKlant) {
		return bedrijfRepo.getBedrijfByKlantId(idKlant);
	}

	public Interface_Leverancier getLeverancierGegevensByIdBedrijf(int idBedrijf) {
		return leverancierRepo.getLeverancierByIdBedrijf(idBedrijf);
	}

	public Interface_Adres getAdresByIdAdres(int idAdres) {
		return adresRepo.getAdresById(idAdres);
	}
	

	

	public void updateLeverancier(Interface_Leverancier lever) {
		leverancierRepo.updateLeverancier(lever);
		
	}
}
