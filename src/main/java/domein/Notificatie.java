package domein;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "notificatie")
public class Notificatie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idNotificatie;
	private String text;
	private String onderwerp;
	private boolean geopend;
	private boolean afgehandeld;
	private String idOrder;

	@Temporal(TemporalType.TIMESTAMP)
	private Date datum;

	public String getIdNotificatie() {
		return this.idNotificatie;
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
	
	public Notificatie(String text, String onderwerp, boolean geopend, Date datum, String idOrder) {
		this.text = text;
		this.onderwerp = onderwerp;
		this.geopend = geopend;
		this.datum = datum;
		this.afgehandeld = false;
		this.idOrder = idOrder;
	}

	public Notificatie() {
    }
	
	@Override
	public String toString() {
		return "Notificatie: idNotificatie: " + idNotificatie +  ", onderwerp: " + onderwerp + ", text: " + text
				+ ", geopend: " + geopend + ", datum: " + datum + "]";
	}

	public void setAfgehandeld(boolean b) {
		this.afgehandeld = b;
	}

	public void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}

}