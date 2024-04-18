package domein;

public class Klant extends Gebruiker {

    private String klantnummer;
    private int idKlant;

    public Klant(String gebruikersnaam, String password_Hash, boolean isActief, String klantnummer, int id, String rol) {
        super(gebruikersnaam, password_Hash, isActief, rol,id);
        this.klantnummer = klantnummer;
    }
}
