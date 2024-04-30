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
public class Klant implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Transient
    private int aantalBestellingen;

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
        this.gebruikersnaam = gebruikersnaam;
        this.klantnummer = klantnummer;
        this.email = email;
        this.idBedrijf = idBedrijf;
    }

    public Klant(String gebruikersnaam, String klantnummer, String[] betaalMethodes, String email) {
        this.gebruikersnaam = gebruikersnaam;
        this.klantnummer = klantnummer;
        this.email = email;
    }

    public int getIdKlant() {
        return idKlant;
    }

    public void setIdKlant(int idKlant) {
        this.idKlant = idKlant;
    }

    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    public void setGebruikersnaam(String gebruikersnaam) {
        this.gebruikersnaam = gebruikersnaam;
    }

    public String getKlantnummer() {
        return klantnummer;
    }

    public void setKlantnummer(String klantnummer) {
        this.klantnummer = klantnummer;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdBedrijf() {
        return idBedrijf;
    }

    public void setIdBedrijf(int idBedrijf) {
        this.idBedrijf = idBedrijf;
    }

	public int getAantalBestellingen() {
		return aantalBestellingen;
	}

	public void setAantalBestellingen(int aantalBestellingen) {
		this.aantalBestellingen = aantalBestellingen;
	}

}
