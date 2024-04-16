package domein;

import java.util.Date;

public class Bedrijf {

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

	public int getIdAdres() {
		return this.idAdres;
	}

	public void setIdAdres(int idAdres) {
		this.idAdres = idAdres;
	}

	public boolean isIsActief() {
		return this.isActief;
	}

	public void setIsActief(boolean isActief) {
		this.isActief = isActief;
	}

	public Bedrijf() {
		// TODO - implement Bedrijf.Bedrijf
		throw new UnsupportedOperationException();
	}

}