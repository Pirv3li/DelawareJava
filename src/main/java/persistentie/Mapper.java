package persistentie;

import domein.Gebruiker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapper {

    private static final String SELECT_GEBRUIKER_BY_USERNAME = 
        "SELECT gebruikersnaam, password_hash  FROM klant WHERE gebruikersnaam = ? " +
        "UNION " +
        "SELECT gebruikersnaam, password_hash FROM leverancier WHERE gebruikersnaam = ?";

    public Gebruiker findGebruikerByUsername(String gebruikersnaam) {
        Gebruiker gebruiker = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL, Connectie.DATABASE_USERNAME, Connectie.DATABASE_PASSWORD);
             PreparedStatement query = conn.prepareStatement(SELECT_GEBRUIKER_BY_USERNAME)) {
            query.setString(1, gebruikersnaam);
            query.setString(2, gebruikersnaam); // Set the parameter for the second query
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("gebruikersnaam");
                    String passwordHash = rs.getString("password_hash");
                    gebruiker = new Gebruiker(username, passwordHash, true);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return gebruiker;
    }
}
