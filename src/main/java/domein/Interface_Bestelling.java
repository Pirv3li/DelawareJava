package domein;

import javafx.beans.property.BooleanProperty;

public interface Interface_Bestelling {

	String getIdOrder();

	int getIdKlant();

	int getIdLeverancier();

	int getIdAdres();

	String getDatum();

	String getOrderStatus();

    boolean getBetalingStatus();

	double getTotaalPrijs();

	BooleanProperty betalingStatusProperty();

}