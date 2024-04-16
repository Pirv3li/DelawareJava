package domein;

public class bestellingDetails {

	private int idOrderDetails;
	private int idProduct;
	private int aantal;

	public int getIdOrderDetails() {
		return this.idOrderDetails;
	}

	public void setIdOrderDetails(int idOrderDetails) {
		this.idOrderDetails = idOrderDetails;
	}

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getAantal() {
		return this.aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	/**
	 * 
	 * @param idOrderDetails
	 * @param idProduct
	 * @param aantal
	 */
	public bestellingDetails(int idOrderDetails, int idProduct, int aantal) {
		// TODO - implement bestellingDetails.bestellingDetails
		throw new UnsupportedOperationException();
	}

}