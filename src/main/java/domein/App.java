package domein;

import persistentie.Mapper;
import repository.BedrijfDao;
import repository.BedrijfDaoJpa;
import repository.LeverancierDao;
import repository.LeverancierDaoJpa;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class App {

	private Mapper mapper;

	private static final int ARGON_ITERATIONS = 6;
	private static final int ARGON_MEMORY = 1 << 17;

	private BedrijfDao bedrijfRepo;
	private LeverancierDao leverancierRepo;

	public App() {
		this.mapper = new Mapper();
		setBedrijfRepo(new BedrijfDaoJpa());
		setLeverancierRepo(new LeverancierDaoJpa());
	}

	public void setBedrijfRepo(BedrijfDao mock) {
		bedrijfRepo = mock;
	}

	public void setLeverancierRepo(LeverancierDao mock) {
		leverancierRepo = mock;
	}

//    public Admin Aanmelden(String gebruikersnaam, String password) {
//    	Administrator admin = mapper.findLeverancierByUsername(gebruikersnaam);
//        if (admin == null || !verifyPassword(password, admin.getPassword_Hash())) { 
//            return null; 
//        }
//        return user;
//    }

	public Leverancier Aanmelden(String gebruikersnaam, String password) {

		LeverancierDaoJpa.startTransaction();
		Leverancier leverancier = leverancierRepo.getLeverancierByGebruikersnaam("leverancier1");
		;
		LeverancierDaoJpa.closePersistency();

		if (leverancier == null || !verifyPassword(password, leverancier.getPassword_Hash())) {
			return null;
		}
		return leverancier;
	}

	public List<Bestelling> FindbestellingenByLeverancier(Leverancier user) {

		List<Bestelling> bestellingen = mapper.findBestellingByLeverancierofKlant(user);

		return bestellingen;
	}

	public List<BestellingDetails> getBestellingDetails(Bestelling bestelling) {

		List<BestellingDetails> bestellingDetails = mapper.getBestellingDetails(bestelling);

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
