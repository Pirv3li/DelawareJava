package domein;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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

		@NamedQuery(name = "Bedrijf.getBedrijven", query = "SELECT b FROM Bedrijf b"),
		@NamedQuery(name = "Bedrijf.getBedrijfById", query = "select b FROM Bedrijf b where b.idBedrijf = :idBedrijf"),})

public class Bedrijf implements Serializable, Interface_Bedrijf {

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

	@Override
	public int getIdBedrijf() {
		return this.idBedrijf;
	}

	private void setIdBedrijf(int idBedrijf) {
		this.idBedrijf = idBedrijf;
	}

	@Override
	public String getNaam() {
		return this.naam;
	}

	private void setNaam(String naam) {
		this.naam = naam;
	}

	@Override
	public String getLogo() {
		return this.logo;
	}

	private void setLogo(String logo) {
		this.logo = logo;
	}

	@Override
	public String getSector() {
		return this.sector;
	}

	private void setSector(String sector) {
		this.sector = sector;
	}

	@Override
	public String getEmail() {
		return this.email;
	}

	private void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String getIban() {
		return this.iban;
	}

	private void setIban(String iban) {
		this.iban = iban;
	}

	@Override
	public String getBtwNummer() {
		return this.btwNummer;
	}

	private void setBtwNummer(String btwNummer) {
		this.btwNummer = btwNummer;
	}

	@Override
	public int getTelefoonnummer() {
		return this.telefoonnummer;
	}

	private void setTelefoonnummer(int telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}

	@Override
	public String getGebruikerSinds() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(this.gebruikerSinds);
	}

	private void setGebruikerSinds(Date gebruikerSinds) {
		this.gebruikerSinds = gebruikerSinds;
	}

	@Override
	public boolean isIsActief() {
		return this.isActief;
	}

	private void setIsActief(boolean isActief) {
		this.isActief = isActief;
	}

	@Override
	public int getIdAdres() {
		return idAdres;
	}

	private void setIdAdres(int idAdres) {
		this.idAdres = idAdres;
	}

	public Bedrijf(int idBedrijf, String naam, String logo, String sector, String email, String iban, String btwNummer,
			int telefoonnummer, Date gebruikerSinds, int idAdres, boolean isActief) {
		setIdBedrijf(idBedrijf);;
		setNaam(naam);
		setLogo(logo);
		setSector(sector);
		setEmail(email);
		setIban(iban);
		setBtwNummer(btwNummer);
		setTelefoonnummer(telefoonnummer);
		setGebruikerSinds(gebruikerSinds);
		setIdAdres(idAdres);
		setIsActief(isActief);
	}

	public Bedrijf() {

	}

}