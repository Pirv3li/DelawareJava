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
public class Bestelling implements Serializable, Interface_Bestelling {

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
		setIdAdres(idAdres);
	}

	public Bestelling() {

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
		String betalingStatus;
		if (this.betalingStatus) {
			betalingStatus = "Betaald";
		} else {
			betalingStatus = "Niet betaald";
		}
		return betalingStatus;
	}

	private void setBetalingStatus(boolean betalingStatus) {
		this.betalingStatus = betalingStatus;
	}

	@Override
	public double getTotaalPrijs() {
		return this.totaalPrijs;
	}

	private void setTotaalPrijs(double totaalPrijs) {
		this.totaalPrijs = totaalPrijs;
	}

}