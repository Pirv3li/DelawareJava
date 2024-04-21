package domein;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "bedrijf")
@NamedQueries({
		@NamedQuery(name = "Bedrijf.getBedrijfById", query = "select b FROM Bedrijf b where b.idBedrijf = :idBedrijf"),

		@NamedQuery(name = "Bedrijf.getBedrijven", query = "SELECT b FROM Bedrijf b") })

public class Bedrijf implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idBedrijf;
	private String naam;
	private String logo;
	private String sector;
	private String email;
	private String iban;
	private String btwNummer;
	private int telefoonnummer;
	private Date gebruikerSinds;
	private int idAdres;
	private boolean isActief;

	public int getIdBedrijf() {
		return this.idBedrijf;
	}

	public void setIdBedrijf(int idBedrijf) {
		this.idBedrijf = idBedrijf;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public String getLogo() {
		return this.logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getSector() {
		return this.sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getIban() {
		return this.iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public String getBtwNummer() {
		return this.btwNummer;
	}

	public void setBtwNummer(String btwNummer) {
		this.btwNummer = btwNummer;
	}

	public int getTelefoonnummer() {
		return this.telefoonnummer;
	}

	public void setTelefoonnummer(int telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	public Date getGebruikerSinds() {
		return this.gebruikerSinds;
	}

	public void setGebruikerSinds(Date gebruikerSinds) {
		this.gebruikerSinds = gebruikerSinds;
	}

	public boolean isIsActief() {
		return this.isActief;
	}

	public void setIsActief(boolean isActief) {
		this.isActief = isActief;
	}

	public int getIdAdres() {
		return idAdres;
	}

	public void setIdAdres(int idAdres) {
		this.idAdres = idAdres;
	}

	public Bedrijf(int idBedrijf, String naam, String logo, String sector, String email, String iban, String btwNummer,
			int telefoonnummer, Date gebruikerSinds, int idAdres, boolean isActief) {
		super();
		this.idBedrijf = idBedrijf;
		this.naam = naam;
		this.logo = logo;
		this.sector = sector;
		this.email = email;
		this.iban = iban;
		this.btwNummer = btwNummer;
		this.telefoonnummer = telefoonnummer;
		this.gebruikerSinds = gebruikerSinds;
		this.idAdres = idAdres;
		this.isActief = isActief;
	}

	public Bedrijf() {

	}

}