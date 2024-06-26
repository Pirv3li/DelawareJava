package domein;


import repository.AdminDao;
import repository.AdresDao;
import repository.LeverancierDao;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.Callback;

public class B2B_Portal {

	private static final int ARGON_ITERATIONS = 6;
	private static final int ARGON_MEMORY = 1 << 17;

    private LeverancierDao leverancierRepo;
    private AdresDao adresRepo;
    private AdminDao adminRepo;

    public B2B_Portal(LeverancierDao leverancierRepo, AdresDao adresRepo,
                      AdminDao adminRepo) {
        this.leverancierRepo = leverancierRepo;
        this.adresRepo = adresRepo;
        this.adminRepo = adminRepo;

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
	
	public ObservableList<Interface_Klant> getKlantenByLeverancierId(int idLeverancier, int aantal, int begin){
		ObservableList<Interface_Klant> klanten = FXCollections.observableArrayList(leverancierRepo.getKlantenByLeverancierID(idLeverancier, aantal, begin));
		
		for (Interface_Klant klant : klanten) {
			setAantalBestellingenByKlant(klant);
		}
		
		return klanten;
	}
	
	public Interface_Klant getKlantById(int idKlant){
		return leverancierRepo.getKlantById(idKlant);
	}

	public ObservableList<Interface_Bestelling> getBestellingenByLeverancierId(Interface_Leverancier user, int aantal, int begin) {
		return FXCollections.observableArrayList(leverancierRepo.getBestellingenByLeverancierId(user.getIdLeverancier(), aantal, begin));
	}

	public ObservableList<Interface_Bestelling> getBestellingenByKlantId(Interface_Klant user) {
		return FXCollections.observableArrayList(leverancierRepo.getBestellingenByKlantId(user.getIdKlant()));
	}
	
	public void setAantalBestellingenByKlant(Interface_Klant klant) {
		List<Bestelling> bestellingen = leverancierRepo.getBestellingenByKlantId(klant.getIdKlant());
		int countUnpaidOrders = 0;
		int countAllorders = 0;

		for (Interface_Bestelling bestelling : bestellingen) {
			countAllorders ++;
		    if (bestelling.getBetalingStatus()=="Niet Betaald" || bestelling.getOrderStatus().contains("niet-verzonden")) {
		        countUnpaidOrders++;
		    }
		}
		klant.setAantalBestellingen(countUnpaidOrders);
		klant.setTotaalBestellingen(countAllorders);
	}
	
	public ObservableList<Interface_BestellingDetails> getBestellingDetails(Interface_Bestelling bestelling) {
		ObservableList<Interface_BestellingDetails> bestellingDetails = FXCollections.observableArrayList(leverancierRepo
				.getBestellingDetailsByOrderId(String.valueOf(bestelling.getIdOrder())));
		return bestellingDetails;
	}

	public Interface_Product getProductByProductId(int id) {
		return leverancierRepo.getProductByProductId(id);
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

	public ObservableList<Interface_Bedrijf> getBedrijven(int aantal, int begin) {
		return FXCollections.observableArrayList(adminRepo.getBedrijven(aantal, begin));
	}
	
	public Interface_Bedrijf getBedrijfByKlantId(int idKlant) {
		return leverancierRepo.getBedrijfByKlantId(idKlant);
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
	
	public void createNotificatie(String string, String string2, boolean b, Date date,
			Interface_Bestelling bestelling) {
		leverancierRepo.createNotificatie(string, string2, b, date,
			bestelling);
		
	}
	
	public ObservableList<Interface_GoedKeuringLeverancier> getAllByStatusAfhandeling(String soort, int aantal, int begin) {
		return FXCollections.observableArrayList(adminRepo.getAllByStatusAfhandeling(soort, aantal, begin));
	}
	
	public void keuringVeranderVerzoekenLeverancier(int id, String afgehandeld) {
		adminRepo.keuringVeranderVerzoekenLeverancier(id, afgehandeld);
		
	}
	
	public void veranderBetalingStatus(String idOrder) {
		leverancierRepo.veranderBetalingStatus(idOrder);
	}
	
	public Interface_Leverancier getLeverancierById(int idLeverancier) {
		return leverancierRepo.getLeverancierById(idLeverancier);
	}
	
	public void updateLeverancierById(int idLeverancier, String gebruikersnaam, String email, String iban, String btwNummer, String telefoonnummer,
			String sector, String straat, String nummer, String stad,String postcode) {
		adminRepo.updateLeverancierById(idLeverancier, gebruikersnaam, email);
		Leverancier l = leverancierRepo.getLeverancierById(idLeverancier);
		Bedrijf b = l.getBedrijf();
		b.setIban(iban);
		b.setBtwNummer(btwNummer);
		b.setTelefoonnummer(telefoonnummer);
		b.setSector(sector);
		int idAdres = adminRepo.createAdres(straat, nummer, stad,postcode);
		adminRepo.updateBedrijfByIdBedrijf(b.getIdBedrijf() ,iban, btwNummer, telefoonnummer, sector, idAdres);
	}
}
