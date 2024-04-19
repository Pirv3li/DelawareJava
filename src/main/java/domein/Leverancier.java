package domein;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "leverancier")
@NamedQuery(name = "Leverancier.getLeverancierByGebruikersnaam", 
            query = "SELECT l FROM Leverancier l WHERE l.gebruikersnaam = :gebruikersnaam")
public class Leverancier extends Gebruiker implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLeverancier")
    private int idLeverancier;

    private int leveranciernummer;
    private String[] betaalMethodes;

    public Leverancier() {
        super();
    }

    public Leverancier(String gebruikersnaam, String password_Hash, 
    		 int leveranciernummer, String[] betaalMethodes, 
    		String roles, int idLeverancier) {
    	super(gebruikersnaam, password_Hash);
        this.leveranciernummer = leveranciernummer;
        this.betaalMethodes = betaalMethodes;
        this.idLeverancier = idLeverancier;
    }
    
    public Leverancier(String gebruikersnaam, String password_Hash, 
    		boolean isActief, int leveranciernummer, 
    		String roles, int idLeverancier) {
    	super(gebruikersnaam, password_Hash);
        this.leveranciernummer = leveranciernummer;
        this.idLeverancier = idLeverancier;
    }
    
    public Leverancier(String gebruikersnaam, String password_Hash, 
    		
    		String roles, int idLeverancier) {
    	super(gebruikersnaam, password_Hash);
        this.leveranciernummer = leveranciernummer;
        this.idLeverancier = idLeverancier;
    }

    public int getIdLeverancier() {
        return idLeverancier;
    }

    public void setIdLeverancier(int idLeverancier) {
        this.idLeverancier = idLeverancier;
    }

    public int getLeveranciernummer() {
        return leveranciernummer;
    }

    public void setLeveranciernummer(int leveranciernummer) {
        this.leveranciernummer = leveranciernummer;
    }

    public String[] getBetaalMethodes() {
        return betaalMethodes;
    }

    public void setBetaalMethodes(String[] betaalMethodes) {
        this.betaalMethodes = betaalMethodes;
    }
}
    

