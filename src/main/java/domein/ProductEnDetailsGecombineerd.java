package domein;

public class ProductEnDetailsGecombineerd {

	private String productNaam;
	private Double eenheidsPrijs;
	private Double btwTarief;
	private int aantal;
	private Double totaalPrijs;

	public ProductEnDetailsGecombineerd() {
	}

	public ProductEnDetailsGecombineerd(String productNaam, Double eenheidsPrijs, Double btwTarief, int aantal) {
		super();
		this.productNaam = productNaam;
		this.eenheidsPrijs = eenheidsPrijs;
		this.btwTarief = btwTarief;
		this.aantal = aantal;
		setTotaalPrijs();

	}

	public String getProductNaam() {
		return productNaam;
	}

	public void setProductNaam(String productNaam) {
		this.productNaam = productNaam;
	}

	public Double getEenheidsPrijs() {
		return eenheidsPrijs;
	}

	public void setEenheidsPrijs(Double eenheidsPrijs) {
		this.eenheidsPrijs = eenheidsPrijs;
	}

	public Double getBtwTarief() {
		return btwTarief;
	}

	public void setBtwTarief(Double btwTarief) {
		this.btwTarief = btwTarief;
	}

	public int getAantal() {
		return aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public Double getTotaalPrijs() {
		return totaalPrijs;
	}

	public void setTotaalPrijs() {
		Double btw = btwTarief / 100.0;
		this.totaalPrijs = (eenheidsPrijs + (eenheidsPrijs * btw)) * aantal;
	}

}
