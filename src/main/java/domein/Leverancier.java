package domein;

public class Leverancier extends Gebruiker {

    private int leveranciernummer;
    private String[] betaalMethodes;

    public Leverancier(String gebruikersnaam, String password_Hash, boolean isActief, int leveranciernummer, int id, String[] betaalMethodes, String rol) {
        super(gebruikersnaam, password_Hash, isActief,rol,id);
        this.leveranciernummer = leveranciernummer;
        this.betaalMethodes = betaalMethodes;
    }

    public String[] getBetaalMethodes() {
        return this.betaalMethodes;
    }

    public void setBetaalMethodes(String[] betaalMethodes) {
        this.betaalMethodes = betaalMethodes;
    }
}
