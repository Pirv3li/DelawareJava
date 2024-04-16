package domein;

import persistentie.Mapper;
import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class App {

    private Mapper mapper; 

    public App() {
        this.mapper = new Mapper();
    }

    public String Aanmelden(String gebruikersnaam, String password) {
        Gebruiker user = mapper.findGebruikerByUsername(gebruikersnaam);
        
        if (user == null || !verifyPassword(password, user.getPassword_Hash())) {
            return "nope"; 
        }
        return "logged in";
    }

    private boolean verifyPassword(String password, String hashedPassword) {
        Argon2 argon2 = Argon2Factory.create();
        try {
            if (argon2.verify(hashedPassword, password.toCharArray())) {
                return true; 
            } else {
                return false; 
            }
        }catch(Error error) {
        	System.out.println(error);
        }
        return false;
    }
}
