package domein;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "notificatie")
public class Notificatie {

	@Id
	private String idNotificatie;
	private String text;
	private String onderwerp;
	private boolean geopend;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;

	@ManyToOne
	@JoinColumn(name = "idOrder")
	private Bestelling bestelling;

	public Bestelling getBestelling() {
		return this.bestelling;
	}

	public void setBestelling(Bestelling bestelling) {
		this.bestelling = bestelling;
	}

	public String getIdNotificatie() {
		return this.idNotificatie;
	}

	public void setIdNotificatie(String idNotificatie) {
		this.idNotificatie = idNotificatie;
	}

	public String getText() {
		return this.text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getOnderwerp() {
		return this.onderwerp;
	}

	public void setOnderwerp(String onderwerp) {
		this.onderwerp = onderwerp;
	}

	public boolean isGeopend() {
		return this.geopend;
	}

	public void setGeopend(boolean geopend) {
		this.geopend = geopend;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	/**
	 * 
	 * @param idNotificatie
	 * @param text
	 * @param onderwerp
	 * @param geopend
	 * @param datum
	 */
	public Notificatie(String idNotificatie, String text, String onderwerp, boolean geopend, Date datum, Bestelling bestelling) {
		// TODO - implement Notificatie.Notificatie
		throw new UnsupportedOperationException();
	}

	public Notificatie() {
    }
	
	@Override
	public String toString() {
		return "Notificatie: idNotificatie: " + idNotificatie +  ", onderwerp: " + onderwerp + ", text: " + text
				+ ", geopend: " + geopend + ", datum: " + datum + "]";
	}

}