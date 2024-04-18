package domein;

public class BestellingDetails {

	private int idOrderDetails;
	private double eenheidsPrijs;
	private int idProduct;
	private String productName;
	private double btwtarief;
	private int aantal;
	
	/**
	 * 
	 * @param idOrderDetails
	 * @param idProduct
	 * @param  
	 * @param aantal
	 */
	public BestellingDetails(int idOrderDetails, double eenheidsPrijs,int idProduct, String productName,double btwtarief, int productQuantity) {
		setIdOrderDetails(idOrderDetails);
		setEenheidsPrijs(eenheidsPrijs);
		setIdProduct(idProduct);
		setAantal(productQuantity);
		setBtwtarief(btwtarief);
		setProductName(productName);
	}
	
	
	public double getEenheidsPrijs() {
		return eenheidsPrijs;
	}
	
	
	public double getTotaalPrijs() {
		return (eenheidsPrijs * aantal) + btwtarief;
	}


	public void setEenheidsPrijs(double eenheidsPrijs) {
		this.eenheidsPrijs = eenheidsPrijs;
	}


	public String getProductName() {
		return productName;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public double getBtwtarief() {
		return btwtarief;
	}


	public void setBtwtarief(double btwtarief) {
		this.btwtarief = btwtarief;
	}


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



}