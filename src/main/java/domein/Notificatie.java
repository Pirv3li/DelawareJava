package domein;

import java.util.Date;

public class Notificatie {

	private String idNotificatie;
	private String text;
	private String onderwerp;
	private boolean geopend;
	Date datum;

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
	public Notificatie(String idNotificatie, String text, String onderwerp, boolean geopend, Date datum) {
		// TODO - implement Notificatie.Notificatie
		throw new UnsupportedOperationException();
	}

}