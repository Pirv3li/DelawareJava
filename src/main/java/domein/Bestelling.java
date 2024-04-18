package domein;

import java.util.Date;

public class Bestelling {

	private String idOrder;
	private int idKlant;
	private int idLeverancier;
	private int idAdres;
	private Date datum;
	private String orderStatus;
	private boolean betalingStatus;
	private double totaalPrijs;

	public Bestelling(String idOrder, int idKlant, int idLeverancier, int idAdres, Date datum, String orderStatus,
			boolean betalingStatus, double totaalPrijs) {
		setIdOrder(idOrder);
		setIdKlant(idKlant);
		setIdLeverancier(idLeverancier);
		setIdKlant(idKlant);		
		setDatum(datum);
		setOrderStatus(orderStatus);
		setBetalingStatus(betalingStatus);
		setTotaalPrijs(totaalPrijs);
	}

	public String getIdOrder() {
		return this.idOrder;
	}

	public void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}

	public int getIdKlant() {
		return this.idKlant;
	}

	public void setIdKlant(int idKlant) {
		this.idKlant = idKlant;
	}

	public int getIdLeverancier() {
		return this.idLeverancier;
	}

	public void setIdLeverancier(int idLeverancier) {
		this.idLeverancier = idLeverancier;
	}

	public int getIdAdres() {
		return this.idAdres;
	}

	public void setIdAdres(int idAdres) {
		this.idAdres = idAdres;
	}

	public Date getDatum() {
		return this.datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	public String getOrderStatus() {
		return this.orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public boolean isBetalingStatus() {
		return this.betalingStatus;
	}

	public void setBetalingStatus(boolean betalingStatus) {
		this.betalingStatus = betalingStatus;
	}

	public double getTotaalPrijs() {
		return this.totaalPrijs;
	}

	public void setTotaalPrijs(double totaalPrijs) {
		this.totaalPrijs = totaalPrijs;
	}
	


}