package domein;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

import java.text.SimpleDateFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.NamedQueries;

@Entity
@Table(name = "`order`")
@NamedQueries({
		@NamedQuery(name = "Bestelling.getBestellingenByLeverancierId", query = "select b FROM Bestelling b where b.idLeverancier = :idLeverancier"),
		@NamedQuery(name = "Bestelling.getBestellingenByKlantId", query = "select b FROM Bestelling b where b.idKlant = :idKlant"),
		@NamedQuery(name = "Bestelling.veranderBetalingStatus", query = "UPDATE Bestelling b Set  b.betalingStatus = :betalingStatus where b.idOrder = :idOrder"),})
public class Bestelling implements Serializable, Interface_Bestelling {
	private transient List<Observer> observers = new ArrayList<>();
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String idOrder;
	private int idKlant;
	private int idLeverancier;
	private int idAdres;
	private Date datum;
	private String orderStatus;
	private double totaalPrijs;
	private boolean betalingStatus;
	



	public Bestelling(String idOrder, int idKlant, int idLeverancier, int idAdres, Date datum, String orderStatus,
		boolean betalingStatus, double totaalPrijs) {
		setIdOrder(idOrder);
		setIdLeverancier(idLeverancier);
		setIdKlant(idKlant);
		setDatum(datum);
		setOrderStatus(orderStatus);
		setBetalingStatus(betalingStatus);
		setTotaalPrijs(totaalPrijs);
		setIdAdres(idAdres);
	}

	public Bestelling() {

	}
	
	
	 @Override
	    public void addObserver(Observer observer) {
	        observers.add(observer);
	        System.out.println(observer);
	    }

	    @Override
	    public void removeObserver(Observer observer) {
	        observers.remove(observer);
	    }

	    private void notifyObservers() {
	        for (Observer observer : observers) {
	        	System.out.println("observers notified");
	            observer.update(this);
	        }
	    }

	@Override
	public String getIdOrder() {
		return this.idOrder;
	}

	private void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}

	@Override
	public int getIdKlant() {
		return this.idKlant;
	}

	private void setIdKlant(int idKlant) {
		this.idKlant = idKlant;
	}

	@Override
	public int getIdLeverancier() {
		return this.idLeverancier;
	}

	private void setIdLeverancier(int idLeverancier) {
		this.idLeverancier = idLeverancier;
	}

	@Override
	public int getIdAdres() {
		return this.idAdres;
	}

	private void setIdAdres(int idAdres) {
		this.idAdres = idAdres;
	}

	@Override
	public String getDatum() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(this.datum);
	}

	private void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public String getOrderStatus() {
		return this.orderStatus;
	}

	private void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	
	@Override
	public String getBetalingStatus() {
	    return betalingStatus ? "Betaald" : "Niet Betaald";
	}

	private void setBetalingStatus(boolean betalingStatus) {
		this.betalingStatus = betalingStatus;
		System.out.println(observers);
		notifyObservers();
	}

	public void updateBetalingStatus(boolean isBetaald) {
		setBetalingStatus(isBetaald);
	}

	@Override
	public double getTotaalPrijs() {
		return this.totaalPrijs;
	}

	private void setTotaalPrijs(double totaalPrijs) {
		this.totaalPrijs = totaalPrijs;
	}

}