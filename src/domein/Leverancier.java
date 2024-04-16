package domein;

public class Leverancier extends Gebruiker {

	private int leveranciernummer;
	private String idLeverancier;
	private String[] betaalMethodes;

	public String[] getBetaalMethodes() {
		return this.betaalMethodes;
	}

	public void setBetaalMethodes(String[] betaalMethodes) {
		this.betaalMethodes = betaalMethodes;
	}

	public Leverancier() {
		// TODO - implement Leverancier.Leverancier
		throw new UnsupportedOperationException();
	}

}