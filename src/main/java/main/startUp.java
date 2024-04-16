package main;
import domein.DomeinController;

public class startUp {
	private static DomeinController dc;
	
	public static void main(String[] args) {
		String gebruikersnaam = "klant1";
		String password = "12345678";
		dc = new DomeinController();
		dc.Aanmelden(gebruikersnaam, password);
	}
}
