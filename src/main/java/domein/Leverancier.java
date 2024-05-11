package domein;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "leverancier")
@NamedQueries({
        @NamedQuery(name = "Leverancier.getLeverancierByGebruikersnaam", query = "SELECT l FROM Leverancier l WHERE l.gebruikersnaam = :gebruikersnaam"),
        @NamedQuery(name = "Leverancier.getLeverancierByIdBedrijf", query = "SELECT l from Leverancier l where l.bedrijf.idBedrijf = :idBedrijf"),
        @NamedQuery(name = "Leverancier.updateLeverancierBetaalMethodes", query = "UPDATE Leverancier l SET l.betaalMethodes = :betaalMethodes WHERE l.idLeverancier = :idLeverancier"),
        @NamedQuery(name = "Leverancier.getLeverancierById", query = "SELECT l from Leverancier l where l.idLeverancier = :idLeverancier"),
        @NamedQuery(name = "Leverancier.updateLeverancierById", query = "UPDATE Leverancier l "
        		+ "SET l.gebruikersnaam = :gebruikersnaam,l.email = :email "
        		+ "WHERE l.idLeverancier = :idLeverancier"),

})
public class Leverancier extends Gebruiker implements Serializable, Interface_Leverancier {

    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    @JoinColumn(name = "idBedrijf", referencedColumnName = "idBedrijf")
    private Bedrijf bedrijf;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idLeverancier")
    private int idLeverancier;

    private String leveranciernummer;
    private String betaalMethodes;
    private String email;

    public Leverancier() {
        super();
    }

    public Leverancier(String gebruikersnaam, String password_Hash,
            String leveranciernummer, String betaalMethodes,
            String roles, int idLeverancier, String email) {
        super(gebruikersnaam, password_Hash);
        setLeveranciernummer(leveranciernummer);
        setBetaalMethodes(betaalMethodes);
        setIdLeverancier(idLeverancier);
        setEmail(email);
    }

    public Leverancier(String gebruikersnaam, String password_Hash,
            String leveranciernummer, String betaalMethodes,
            String roles, int idLeverancier) {
        super(gebruikersnaam, password_Hash);
        setLeveranciernummer(leveranciernummer);
        setBetaalMethodes(betaalMethodes);
        setIdLeverancier(idLeverancier);
    }

    public Leverancier(String gebruikersnaam, String password_Hash,
            boolean isActief, String leveranciernummer,
            String roles, int idLeverancier) {
        super(gebruikersnaam, password_Hash);
        setLeveranciernummer(leveranciernummer);
        setIdLeverancier(idLeverancier);
    }

    public Leverancier(String gebruikersnaam, String password_Hash,
            String roles, int idLeverancier) {
        super(gebruikersnaam, password_Hash);
        setLeveranciernummer(leveranciernummer);
    }

    @Override
	public int getIdLeverancier() {
        return idLeverancier;
    }

    private void setIdLeverancier(int idLeverancier) {
        this.idLeverancier = idLeverancier;
    }

    @Override
	public String getLeveranciernummer() {
        return leveranciernummer;
    }

    private void setLeveranciernummer(String leveranciernummer) {
        this.leveranciernummer = leveranciernummer;
    }

    @Override
	public String getBetaalMethodes() {
        if (betaalMethodes != null) {
            return String.join(", ", betaalMethodes);
        } else {
            return "";
        }
    }
    
    private void setEmail(String email) {
    	this.email = email;
    }

    public void setBetaalMethodes(String betaalMethodes) {
        this.betaalMethodes = betaalMethodes;
    }
    
	@Override
	public Bedrijf getBedrijf() {
		return bedrijf;
	}
	
	@Override
    public void setBedrijf(Bedrijf bedrijf) {
        this.bedrijf = bedrijf;
    }

	@Override
	public String getEmail() {
        return this.email;
    }
}
