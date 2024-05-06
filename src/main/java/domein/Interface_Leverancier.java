package domein;

public interface Interface_Leverancier extends Interface_Gebruiker{

	int getIdLeverancier();

	String getLeveranciernummer();

	String getBetaalMethodes();

	Bedrijf getBedrijf();

	String getEmail();

	void setBetaalMethodes(String selectedPaymentMethods);
	
}