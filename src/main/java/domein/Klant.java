package domein;

import java.io.Serializable;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "klant")
@NamedQueries({
    @NamedQuery(name = "Klant.getKlantenByLeverancierID", query = "SELECT DISTINCT k FROM Klant k JOIN Bestelling o ON k.idKlant = o.klantId WHERE o.idLeverancier = :idLeverancier"),
    @NamedQuery(name = "Klant.getKlantById", query = "SELECT k FROM Klant k WHERE k.idKlant = :idKlant")
})
public class Klant implements Serializable, Interface_Klant {

    private static final long serialVersionUID = 1L;
    
    @Transient
    private int aantalBestellingen;
    
    @Transient
    private int totaalBestellingen;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idKlant")
    private int idKlant;
    private String gebruikersnaam;
    private int idBedrijf;
    private String klantnummer;
    private String email;

    public Klant() {
        super();
    }

    public Klant(String gebruikersnaam, String klantnummer, String[] betaalMethodes, String email, int idBedrijf) {
        setGebruikersnaam(gebruikersnaam);
        setKlantnummer(klantnummer);
        setEmail(email);
        setIdBedrijf(idBedrijf);
    }

    public Klant(String gebruikersnaam, String klantnummer, String[] betaalMethodes, String email) {
        setGebruikersnaam(gebruikersnaam);
        setKlantnummer(klantnummer);
        setEmail(email);
    }

    @Override
	public int getIdKlant() {
        return idKlant;
    }

    @Override
	public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    private void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    @Override
	public String getKlantnummer() {
        return klantnummer;
    }

    private void setKlantnummer(String klantnummer) {
        this.klantnummer = klantnummer;
    }

    @Override
	public String getEmail() {
        return this.email;
    }

    private void setEmail(String email) {
        this.email = email;
    }

    @Override
	public int getIdBedrijf() {
        return idBedrijf;
    }

    private void setIdBedrijf(int idBedrijf) {
        this.idBedrijf = idBedrijf;
    }

	@Override
	public int getAantalBestellingen() {
		return aantalBestellingen;
	}

	@Override
	public int getTotaalBestellingen() {
		return totaalBestellingen;
	}
	
	@Override
	public void setAantalBestellingen(int aantalBestellingen) {
		this.aantalBestellingen = aantalBestellingen;
	}

	@Override
	public void setTotaalBestellingen(int aantalBestellingen) {
		this.totaalBestellingen = aantalBestellingen;
	}
}
