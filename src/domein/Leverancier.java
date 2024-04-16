package domein;

public class Leverancier extends Gebruiker {

    private int leveranciernummer;
    private String idLeverancier;
    private String[] betaalMethodes;

    public Leverancier(String gebruikersnaam, String password_Hash, boolean isActief, int leveranciernummer, String idLeverancier, String[] betaalMethodes) {
        super(gebruikersnaam, password_Hash, isActief);
        this.leveranciernummer = leveranciernummer;
        this.idLeverancier = idLeverancier;
        this.betaalMethodes = betaalMethodes;
    }

    public String[] getBetaalMethodes() {
        return this.betaalMethodes;
    }

    public void setBetaalMethodes(String[] betaalMethodes) {
        this.betaalMethodes = betaalMethodes;
    }
}
