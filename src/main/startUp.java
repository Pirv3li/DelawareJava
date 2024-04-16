package main;

import cui.App;
import domein.DomeinController;

public class startUp {

	public static void main(String[] args) {
		DomeinController dc = new DomeinController();
		App app = new App(dc);
		app.start();
		;
	}
}
