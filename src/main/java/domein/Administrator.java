package domein;

public class Administrator extends Gebruiker {


    public Administrator(String gebruikersnaam, String password_Hash, boolean isActief, int id, String rol) {
        super(gebruikersnaam, password_Hash, isActief,rol,id);
    }
}
