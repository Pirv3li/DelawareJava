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
@Table(name = "goedkeuringleverancier")
@NamedQueries({
    @NamedQuery(name = "GoedKeuringLeverancier.updateLeverancierBetaalMethodes", query = "UPDATE GoedKeuringLeverancier g SET g.afgehandeld = :afgehandeld WHERE g.idGoedkeuringLeverancier = :id"),
    @NamedQuery(name = "GoedKeuringLeverancier.getAllByStatusAfhandeling", query = "select g FROM GoedKeuringLeverancier g where g.afgehandeld = :afgehandeld"),
    })

public class GoedKeuringLeverancier implements Serializable, Interface_GoedKeuringLeverancier{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idGoedkeuringLeverancier;

	private String leverancierNummer;
	private String gebruikersnaam;
	private String email;
	private boolean isActief;
	private String roles;
	private String iban;
	private String btwNummer;
	private String telefoonnummer;
	private String sector;
	private String straat;
	private String nummer;
	private String stad;
	private String postcode;
	private String afgehandeld;
	private Date datumAanvraag;
	private int idLeverancier;
	
	
	
	
	public GoedKeuringLeverancier() {
		
	};
	public GoedKeuringLeverancier(int idGoedkeuringLeverancier, String leverancierNummer, String gebruikersnaam,
			String email, boolean isActief, String roles, String iban, String btwNummer, String telefoonnummer,
			String sector, String straat, String nummer, String stad, String postcode, String afgehandeld,
			Date datumAanvraag, int idLeverancier) {
		super();
		this.idGoedkeuringLeverancier = idGoedkeuringLeverancier;
		this.leverancierNummer = leverancierNummer;
		this.gebruikersnaam = gebruikersnaam;
		this.email = email;
		this.isActief = isActief;
		this.roles = roles;
		this.iban = iban;
		this.btwNummer = btwNummer;
		this.telefoonnummer = telefoonnummer;
		this.sector = sector;
		this.straat = straat;
		this.nummer = nummer;
		this.stad = stad;
		this.postcode = postcode;
		this.afgehandeld = afgehandeld;
		this.datumAanvraag = datumAanvraag;
		this.idLeverancier = idLeverancier;
	}
	
	
	
	
	public void setIdGoedkeuringLeverancier(int idGoedkeuringLeverancier) {
		this.idGoedkeuringLeverancier = idGoedkeuringLeverancier;
	}




	public void setGebruikersnaam(String gebruikersnaam) {
		this.gebruikersnaam = gebruikersnaam;
	}




	public void setEmail(String email) {
		this.email = email;
	}




	public void setActief(boolean isActief) {
		this.isActief = isActief;
	}




	public void setIban(String iban) {
		this.iban = iban;
	}




	public void setBtwNummer(String btwNummer) {
		this.btwNummer = btwNummer;
	}




	public void setTelefoonnummer(String telefoonnummer) {
		this.telefoonnummer = telefoonnummer;
	}




	public void setSector(String sector) {
		this.sector = sector;
	}




	public void setStraat(String straat) {
		this.straat = straat;
	}




	public void setNummer(String nummer) {
		this.nummer = nummer;
	}




	public void setStad(String stad) {
		this.stad = stad;
	}




	public void setPostcode(String postcode) {
		this.postcode = postcode;
	}




	public void setAfgehandeld(String afgehandeld) {
		this.afgehandeld = afgehandeld;
	}




	public void setIdLeverancier(int idLeverancier) {
		this.idLeverancier = idLeverancier;
	}




	@Override
	public int getidGoedkeuringLeverancier() {
	    return this.idGoedkeuringLeverancier;
	}
	@Override
	public String getleverancierNummer() {
		        return this.leverancierNummer;
	}
	@Override
	public String getgebruikersnaam() {
		return this.gebruikersnaam;
	}
	@Override
	public String getemail() {
		return this.email;
	}
	@Override
	public boolean getisActief() {
		return this.isActief;
	}
	@Override
	public String getroles() {
		return this.roles;
	}
	@Override
	public String getiban() {
		return this.iban;
	}
	@Override
	public String getbtwNummer() {
		return this.btwNummer;
	}
	@Override
	public String gettelefoonnummer() {
		return this.telefoonnummer;
	}
	@Override
	public String getsector() {
		return this.sector;
	}
	@Override
	public String getstraat() {
		return this.straat;
	}
	@Override
	public String getnummer() {
		return this.nummer;
	}
	@Override
	public String getstad() {
		return this.stad;
	}
	@Override
	public String getpostcode() {
		return this.postcode;
	}
	@Override
	public String getafgehandeld() {
		return this.afgehandeld;
	}
	@Override
	public Date getdatumAanvraag() {
		return this.datumAanvraag;
	}
	@Override
	public int getidLeverancier() {
		return this.idLeverancier;
	}
}
