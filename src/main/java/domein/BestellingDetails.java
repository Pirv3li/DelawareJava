package domein;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(name = "`orderdetails`")
@NamedQueries({
		@NamedQuery(name = "BestellingDetails.getBestellingDetailsByOrderId", query = "select b FROM BestellingDetails b where b.idOrder = :idOrder")
})

public class BestellingDetails implements Serializable, Interface_BestellingDetails {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idOrderDetails;

	private double eenheidsPrijs;
	private int idProduct;
	private String idOrder;
	private int aantal;

	public BestellingDetails(int idOrderDetails, double eenheidsPrijs, int idProduct, int productQuantity,
			String idOrder) {
		setIdOrderDetails(idOrderDetails);
		setEenheidsPrijs(eenheidsPrijs);
		setIdProduct(idProduct);
		setAantal(productQuantity);
		setIdOrder(idOrder);
	}

	public BestellingDetails() {

	}

	@Override
	public double getEenheidsPrijs() {
		return eenheidsPrijs;
	}

	@Override
	public double getTotaalPrijs() {

		return (eenheidsPrijs * aantal) + 0;

		// 0 MOET PRODUCT BTW WORDEN
	}

	private void setEenheidsPrijs(double eenheidsPrijs) {
		this.eenheidsPrijs = eenheidsPrijs;
	}

	@Override
	public int getIdOrderDetails() {
		return this.idOrderDetails;
	}

	private void setIdOrderDetails(int idOrderDetails) {
		this.idOrderDetails = idOrderDetails;
	}

	@Override
	public int getIdProduct() {
		return this.idProduct;
	}

	private void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	@Override
	public int getAantal() {
		return this.aantal;
	}

	private void setAantal(int aantal) {
		this.aantal = aantal;
	}

	@Override
	public String getIdOrder() {
		return idOrder;
	}

	private void setIdOrder(String idOrder) {
		this.idOrder = idOrder;
	}

}