package domein;

import repository.KlantDao;
import repository.KlantDaoJpa;
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
import repository.LeverancierDao;
import repository.LeverancierDaoJpa;
import repository.ProductDao;
import repository.ProductDaoJpa;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import jakarta.persistence.EntityNotFoundException;

public class App {

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

	public App() {
		setBedrijfRepo(new BedrijfDaoJpa());
		setLeverancierRepo(new LeverancierDaoJpa());
		setBestellingRepo(new BestellingDaoJpa());
		setBestellingDetailsRepo(new BestellingDetailsDaoJpa());
		setProductRepo(new ProductDaoJpa());
		setAdresRepo(new AdresDaoJpa());
		setAdminRepo(new AdminDaoJpa());
		setKlantRepo(new KlantDaoJpa());
	}
	
	public void setKlantRepo(KlantDao mock) {
		klantRepo = mock;
	}

	public void setBedrijfRepo(BedrijfDao mock) {
		bedrijfRepo = mock;
	}
	
	public void setAdminRepo(AdminDao mock) {
		adminRepo = mock;
	}

	public void setAdresRepo(AdresDao mock) {
		adresRepo = mock;
	}

	public void setLeverancierRepo(LeverancierDao mock) {
		leverancierRepo = mock;
	}

	public void setBestellingRepo(BestellingDao mock) {
		bestellingRepo = mock;
	}

	public void setBestellingDetailsRepo(BestellingDetailsDao mock) {
		bestellingDetailsRepo = mock;
	}

	public void setProductRepo(ProductDao mock) {
		productRepo = mock;
	}

	public Admin AanmeldenAdmin(String gebruikersnaam, String password) {
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

	public Leverancier Aanmelden(String gebruikersnaam, String password) {
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
	
	public List<Klant> getKlantenByLeverancierId(int idLeverancier){
		return klantRepo.getKlantenByLeverancierID(idLeverancier);
	}
	
	public Klant getKlantById(int idKlant){
		return klantRepo.getKlantById(idKlant);
	}

	public List<Bestelling> getBestellingenByLeverancierId(Leverancier user) {
		return bestellingRepo.getBestellingenByLeverancierId(user.getIdLeverancier());
	}

	public List<Bestelling> getBestellingenByKlantId(Klant user) {
		return bestellingRepo.getBestellingenByKlantId(user.getIdKlant());
	}
	
	public void setAantalBestellingenByKlant(Klant klant) {
		List<Bestelling> bestellingen = bestellingRepo.getBestellingenByKlantId(klant.getIdKlant());
		int countUnpaidOrders = 0;

		for (Bestelling bestelling : bestellingen) {
		    if (bestelling.getBetalingStatus()=="Niet betaald") {
		        countUnpaidOrders++;
		    }
		}
		klant.setAantalBestellingen(countUnpaidOrders);
	}
	
	public List<BestellingDetails> getBestellingDetails(Bestelling bestelling) {
		List<BestellingDetails> bestellingDetails = bestellingDetailsRepo
				.getBestellingDetailsByOrderId(String.valueOf(bestelling.getIdOrder()));
		return bestellingDetails;
	}

	public Product getProductByProductId(int id) {
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

	public List<Bedrijf> getBedrijven() {
		return bedrijfRepo.getBedrijven();
	}

	public Leverancier getLeverancierGegevensByIdBedrijf(int idBedrijf) {
		return leverancierRepo.getLeverancierByIdBedrijf(idBedrijf);
	}

	public Adres getAdresByIdAdres(int idAdres) {
		return adresRepo.getAdresById(idAdres);
	}
}
