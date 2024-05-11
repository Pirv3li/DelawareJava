package domein;

import javafx.beans.property.BooleanProperty;

public interface Interface_Bestelling {

	String getIdOrder();

	int getIdKlant();

	int getIdLeverancier();

	int getIdAdres();

	String getDatum();

	String getOrderStatus();

    String getBetalingStatus();

	double getTotaalPrijs();
	
    void addObserver(Observer observer);
    
    void removeObserver(Observer observer);

}