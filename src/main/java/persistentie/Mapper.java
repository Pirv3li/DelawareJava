package persistentie;

import domein.Bestelling;
import domein.Gebruiker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;

public class Mapper {

    private static final String SELECT_GEBRUIKER_BY_USERNAME = 
        "SELECT gebruikersnaam, password_hash, roles, idKlant  FROM klant WHERE gebruikersnaam = ? " +
        "UNION " +
        "SELECT gebruikersnaam, password_hash, roles, idLeverancier  FROM leverancier WHERE gebruikersnaam = ?";
    
    private static final String SELECT_ORDER_BY_IDLeverancier = 
    		"SELECT idOrder, idKlant, idLeverancier, idAdres, datum,orderStatus, betalingStatus, totaalPrijs FROM SDP2_2324_DB_G14.order "
    		+ "where idLeverancier = ?;";

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
                    String rolesJson = rs.getString("roles"); // Retrieve the JSON array as a string
                    String[] rolesArray = new Gson().fromJson(rolesJson, String[].class); // Parse the JSON array
                    String rol = rolesArray[0]; // Extract the first element
                    
                    int id = 0;
                    if(rol == "klant") {
                    	 id = rs.getInt("idLeverancier");
                    }else {
                   	 id = rs.getInt("idKlant");

                    }
                    System.out.println(rol);
                    gebruiker = new Gebruiker(username, passwordHash, true,rol,id);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return gebruiker;
    }
    

    
    public List<Bestelling> findBestellingByLeverancierofKlant(Gebruiker user) {
        List<Bestelling> bestellingen = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL, Connectie.DATABASE_USERNAME, Connectie.DATABASE_PASSWORD);
             PreparedStatement query = conn.prepareStatement(SELECT_ORDER_BY_IDLeverancier)) {
        	int id = user.getId();
            query.setString(1, String.valueOf(id));
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String idOrder = rs.getString("idOrder");
                    int idKlant = rs.getInt("idKlant");
                    int idLeverancier = rs.getInt("idLeverancier");
                    int idAdres = rs.getInt("idAdres");
                    Date datum = rs.getDate("datum");
                    String orderStatus = rs.getString("orderStatus");
                    boolean betalingStatus = rs.getBoolean("betalingStatus");
                    double totaalPrijs = rs.getDouble("totaalPrijs");

                    Bestelling bestelling = new Bestelling(idOrder, idKlant, idLeverancier, idAdres, datum, orderStatus, betalingStatus, totaalPrijs);
                    System.out.println(bestelling);
                    bestellingen.add(bestelling);
                    
                }
                System.out.println(bestellingen);

            }
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return bestellingen;
    }
}
