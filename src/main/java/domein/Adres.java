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
@Table(name = "adres")
@NamedQueries({
		@NamedQuery(name = "Adres.getAdresById", query = "select  a FROM Adres a where a.idAdres = :idAdres") })

public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAdres;
	private String straat;
	private int nummer;
	private String stad;
	private int postcode;
	private Date laatstGebruikt;

	public Adres(int idAdres, String straat, int nummer, String stad, int postcode, Date laatstGebruikt) {
		super();
		this.idAdres = idAdres;
		this.straat = straat;
		this.nummer = nummer;
		this.stad = stad;
		this.postcode = postcode;
		this.laatstGebruikt = laatstGebruikt;
	}

	public Adres() {
		super();
	}

	public int getIdAdres() {
		return idAdres;
	}

	public void setIdAdres(int idAdres) {
		this.idAdres = idAdres;
	}

	public void setStraat(String straat) {
		this.straat = straat;
	}

	public int getNummer() {
		return nummer;
	}

	public void setNummer(int nummer) {
		this.nummer = nummer;
	}

	public void setStad(String stad) {
		this.stad = stad;
	}

	public int getPostcode() {
		return postcode;
	}

	public void setPostcode(int postcode) {
		this.postcode = postcode;
	}

	public Date getLaatstGebruikt() {
		return laatstGebruikt;
	}

	public void setLaatstGebruikt(Date laatstGebruikt) {
		this.laatstGebruikt = laatstGebruikt;
	}

	public String getStraat() {
		return straat + " " + nummer;
	}

	public String getStad() {
		return stad + ", " + postcode;
	}

}
