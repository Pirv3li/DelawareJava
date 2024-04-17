package domein;

import persistentie.Mapper;

import java.nio.charset.StandardCharsets;

import de.mkammerer.argon2.Argon2;
import de.mkammerer.argon2.Argon2Factory;

public class App {

    private Mapper mapper; 
    
    private static final int ARGON_ITERATIONS = 6;
    private static final int ARGON_MEMORY = 1 << 17;

    public App() {
        this.mapper = new Mapper();
    }

    public Gebruiker Aanmelden(String gebruikersnaam, String password) {
        Gebruiker user = mapper.findGebruikerByUsername(gebruikersnaam);
        if (user == null || !verifyPassword(password, user.getPassword_Hash())) { 
            return null; 
        }
        return user;
    }

    private boolean verifyPassword(String password, String hashedPassword) {
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id, ARGON_ITERATIONS, ARGON_MEMORY);

        try {
            return argon2.verify(hashedPassword, password.toCharArray(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            System.out.println("Error verifying password: " + e.getMessage());
            return false;
        }
    }
}
