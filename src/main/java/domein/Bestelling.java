package domein;

import java.io.Serializable;
import java.util.Date;
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
public class Bestelling implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

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

	public Bestelling() {

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

	public String getDatum() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(this.datum);
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

	public String getBetalingStatus() {
		String betalingStatus;
		if (this.betalingStatus) {
			betalingStatus = "Betaald";
		} else {
			betalingStatus = "Niet betaald";
		}
		return betalingStatus;
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