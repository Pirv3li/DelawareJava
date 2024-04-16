package domein;

public class Product {

	private int idProduct;
	private int idLeverancier;
	private String naam;
	private double eenheidsprijs;
	private double btwTarief;
	private String foto;
	private int aantal;
	private String beschrijving;
	private String categorie;

	public int getIdProduct() {
		return this.idProduct;
	}

	public void setIdProduct(int idProduct) {
		this.idProduct = idProduct;
	}

	public int getIdLeverancier() {
		return this.idLeverancier;
	}

	public void setIdLeverancier(int idLeverancier) {
		this.idLeverancier = idLeverancier;
	}

	public String getNaam() {
		return this.naam;
	}

	public void setNaam(String naam) {
		this.naam = naam;
	}

	public double getEenheidsprijs() {
		return this.eenheidsprijs;
	}

	public void setEenheidsprijs(double eenheidsprijs) {
		this.eenheidsprijs = eenheidsprijs;
	}

	public double getBtwTarief() {
		return this.btwTarief;
	}

	public void setBtwTarief(double btwTarief) {
		this.btwTarief = btwTarief;
	}

	public String getFoto() {
		return this.foto;
	}

	public void setFoto(String foto) {
		this.foto = foto;
	}

	public int getAantal() {
		return this.aantal;
	}

	public void setAantal(int aantal) {
		this.aantal = aantal;
	}

	public String getBeschrijving() {
		return this.beschrijving;
	}

	public void setBeschrijving(String beschrijving) {
		this.beschrijving = beschrijving;
	}

	public String getCategorie() {
		return this.categorie;
	}

	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	/**
	 * 
	 * @param idProduct
	 * @param idLeverancier
	 * @param naam
	 * @param eenheidsprijs
	 * @param btwTarief
	 * @param foto
	 * @param aantal
	 * @param beschrijving
	 * @param categorie
	 */
	public Product(int idProduct, int idLeverancier, String naam, double eenheidsprijs, double btwTarief, String foto, int aantal, String beschrijving, String categorie) {
		// TODO - implement Product.Product
		throw new UnsupportedOperationException();
	}

}