package domein;

import repository.BedrijfDao;
import repository.BedrijfDaoJpa;
import repository.BestellingDao;
import repository.BestellingDaoJpa;
import repository.BestellingDetailsDao;
import repository.BestellingDetailsDaoJpa;
import repository.LeverancierDao;
import repository.LeverancierDaoJpa;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class App {


	private static final int ARGON_ITERATIONS = 6;
	private static final int ARGON_MEMORY = 1 << 17;

	private BedrijfDao bedrijfRepo;
	private LeverancierDao leverancierRepo;
	private BestellingDao bestellingRepo;
	private BestellingDetailsDao bestellingDetailsRepo;



	public App() {
		setBedrijfRepo(new BedrijfDaoJpa());
		setLeverancierRepo(new LeverancierDaoJpa());
		setBestellingRepo(new BestellingDaoJpa());
		setBestellingDetailsRepo(new BestellingDetailsDaoJpa());

	}

	public void setBedrijfRepo(BedrijfDao mock) {
		bedrijfRepo = mock;
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
//    public Admin Aanmelden(String gebruikersnaam, String password) {
//    	Administrator admin = mapper.findLeverancierByUsername(gebruikersnaam);
//        if (admin == null || !verifyPassword(password, admin.getPassword_Hash())) { 
//            return null; 
//        }
//        return user;
//    }

    public Leverancier Aanmelden(String gebruikersnaam, String password) {
        Leverancier leverancier = leverancierRepo.getLeverancierByGebruikersnaam(gebruikersnaam);
        if (leverancier != null && verifyPassword(password, leverancier.getPassword_Hash())) {
            return leverancier;
        }
        return null;
    }

    public List<Bestelling> getBestellingenByLeverancierId(Leverancier user) {
        return bestellingRepo.getBestellingenByLeverancierId(user.getIdLeverancier());
    }

	public List<BestellingDetails> getBestellingDetails(Bestelling bestelling) {

		List<BestellingDetails> bestellingDetails = bestellingDetailsRepo.getBestellingDetailsByOrderId(String.valueOf(bestelling.getIdOrder()));
		for (BestellingDetails bestellingDetails2 : bestellingDetails) {
			System.out.println(bestellingDetails2.getIdOrder());
		}
		return bestellingDetails;
	}

	private boolean verifyPassword(String password, String hashedPassword) {
		Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, ARGON_ITERATIONS, ARGON_MEMORY);

		try {
			return argon2.verify(hashedPassword, password.toCharArray(), StandardCharsets.UTF_8);
		} catch (Exception e) {
			System.out.println("Error verifying password: " + e.getMessage());
			return false;
		}
	}
}
