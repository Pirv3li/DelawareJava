package domein;

import java.util.Date;

public interface Interface_Adres {

	int getIdAdres();

	int getNummer();

	int getPostcode();

	Date getLaatstGebruikt();

	String getStraat();

	String getStad();

	String toString();

}