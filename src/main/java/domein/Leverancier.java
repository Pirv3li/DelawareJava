package domein;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "leverancier")
@NamedQueries({
		@NamedQuery(name = "Leverancier.getLeverancierByGebruikersnaam", 
		            query = "SELECT l FROM Leverancier l WHERE l.gebruikersnaam = :gebruikersnaam"),
		@NamedQuery(name = "Leverancier.getLeverancierByIdBedrijf",
					query = "SELECT l from Leverancier l where l.idBedrijf = :idBedrijf")})
public class Leverancier extends Gebruiker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLeverancier")
    private int idLeverancier;
    private int idBedrijf;
    private String leveranciernummer;
    private String[] betaalMethodes;
    private String email;

    public Leverancier() {
        super();
    }
    
    public Leverancier(String gebruikersnaam, String password_Hash, 
      		 String leveranciernummer, String[] betaalMethodes, 
      		String roles, int idLeverancier, String email, int idBedrijf) {
      	super(gebruikersnaam, password_Hash);
          this.leveranciernummer = leveranciernummer;
          this.betaalMethodes = betaalMethodes;
          this.idLeverancier = idLeverancier;
          this.email = email;
          this.idBedrijf = idBedrijf;
      }
    
    public Leverancier(String gebruikersnaam, String password_Hash, 
   		 String leveranciernummer, String[] betaalMethodes, 
   		String roles, int idLeverancier, String email) {
   	super(gebruikersnaam, password_Hash);
       this.leveranciernummer = leveranciernummer;
       this.betaalMethodes = betaalMethodes;
       this.idLeverancier = idLeverancier;
       this.email = email;
   }
    public Leverancier(String gebruikersnaam, String password_Hash, 
    		 String leveranciernummer, String[] betaalMethodes, 
    		String roles, int idLeverancier) {
    	super(gebruikersnaam, password_Hash);
        this.leveranciernummer = leveranciernummer;
        this.betaalMethodes = betaalMethodes;
        this.idLeverancier = idLeverancier;
    }
    
    public Leverancier(String gebruikersnaam, String password_Hash, 
    		boolean isActief, String leveranciernummer, 
    		String roles, int idLeverancier) {
    	super(gebruikersnaam, password_Hash);
        this.leveranciernummer = leveranciernummer;
        this.idLeverancier = idLeverancier;
    }
    
    public Leverancier(String gebruikersnaam, String password_Hash, 
    		
    		String roles, int idLeverancier) {
    	super(gebruikersnaam, password_Hash);
        this.idLeverancier = idLeverancier;
    }

    public int getIdLeverancier() {
        return idLeverancier;
    }

    public void setIdLeverancier(int idLeverancier) {
        this.idLeverancier = idLeverancier;
    }

    public String getLeveranciernummer() {
        return leveranciernummer;
    }

    public void setLeveranciernummer(String leveranciernummer) {
        this.leveranciernummer = leveranciernummer;
    }

    public String getBetaalMethodes() {
        if (betaalMethodes != null) {
            return String.join(", ", betaalMethodes);
        } else {
            return "";
        }
    }


    public void setBetaalMethodes(String[] betaalMethodes) {
        this.betaalMethodes = betaalMethodes;
    }

	public String getEmail() {
		return this.email;
	}
}
    

