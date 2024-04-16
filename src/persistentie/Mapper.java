package persistentie;

import domein.Gebruiker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Mapper {

    private static final String SELECT_GEBRUIKER_BY_USERNAME = "SELECT gebruikersnaam, password_hash, isActief FROM gebruikers WHERE gebruikersnaam = ?";

    public Gebruiker findGebruikerByUsername(String gebruikersnaam) {
        Gebruiker gebruiker = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL, Connectie.DATABASE_USERNAME, Connectie.DATABASE_PASSWORD);
                PreparedStatement query = conn.prepareStatement(SELECT_GEBRUIKER_BY_USERNAME)) {
            query.setString(1, gebruikersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("gebruikersnaam");
                    String passwordHash = rs.getString("password_hash");
                    boolean isActive = rs.getBoolean("isActief");
                    gebruiker = new Gebruiker(username, passwordHash, isActive);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return gebruiker;
    }
}
