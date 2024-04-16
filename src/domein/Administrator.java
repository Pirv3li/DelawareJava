package domein;

public class Administrator extends Gebruiker {

    private int idAdmin;

    public Administrator(String gebruikersnaam, String password_Hash, boolean isActief, int idAdmin) {
        super(gebruikersnaam, password_Hash, isActief);
        this.idAdmin = idAdmin;
    }
}
