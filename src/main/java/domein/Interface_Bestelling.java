package domein;

public interface Interface_Bestelling {

	String getIdOrder();

	int getIdKlant();

	int getIdLeverancier();

	int getIdAdres();

	String getDatum();

	String getOrderStatus();

	String getBetalingStatus();

	double getTotaalPrijs();

}