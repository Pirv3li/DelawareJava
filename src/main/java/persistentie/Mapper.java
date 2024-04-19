package persistentie;

import domein.Bestelling;
import domein.Gebruiker;
import domein.Leverancier;
import domein.BestellingDetails;

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

    private static final String SELECT_Leverancier_BY_USERNAME = 

        "SELECT gebruikersnaam, password_hash,roles, idLeverancier, betaalmethodes  FROM leverancier WHERE gebruikersnaam = ?";
    
    private static final String SELECT_ORDER_BY_IDLeverancier = 
    		"SELECT idOrder, idKlant, idLeverancier, idAdres, datum,orderStatus, betalingStatus, totaalPrijs FROM SDP2_2324_DB_G14.order "
    		+ "where idLeverancier = ?;";
    
    private static final String SELECT_BESTELLING_DETAILS = 
    	    "SELECT od.idOrderDetails, od.eenheidsPrijs, od.aantal, od.idOrder, od.idProduct, " +
    	    "p.naam AS productName, p.btwtarief, p.aantal AS productQuantity " +
    	    "FROM orderdetails od " +
    	    "JOIN product p ON od.idProduct = p.idProduct " +
    	    "WHERE od.idOrder = ?";


    public Leverancier findLeverancierByUsername(String gebruikersnaam) {
        Leverancier gebruiker = null;

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL, Connectie.DATABASE_USERNAME, Connectie.DATABASE_PASSWORD);
             PreparedStatement query = conn.prepareStatement(SELECT_Leverancier_BY_USERNAME)) {
            query.setString(1, gebruikersnaam);
            try (ResultSet rs = query.executeQuery()) {
                if (rs.next()) {
                    String username = rs.getString("gebruikersnaam");
                    String passwordHash = rs.getString("password_hash");
                    String rolesJson = rs.getString("roles"); // Retrieve the JSON array as a string
                    String[] rolesArray = new Gson().fromJson(rolesJson, String[].class); // Parse the JSON array
                    String rol = rolesArray[0]; // Extract the first element
                    int id = rs.getInt("idLeverancier");

                    gebruiker = new Leverancier(username, passwordHash ,rol,id);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return gebruiker;
    }
    

    
    public List<Bestelling> findBestellingByLeverancierofKlant(Leverancier leverancier) {
        List<Bestelling> bestellingen = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL, Connectie.DATABASE_USERNAME, Connectie.DATABASE_PASSWORD);
             PreparedStatement query = conn.prepareStatement(SELECT_ORDER_BY_IDLeverancier)) {
        	int id = leverancier.getIdLeverancier();
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
                    bestellingen.add(bestelling);
                    
                }

            }
            
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return bestellingen;
    }
    
    
    public List<BestellingDetails> getBestellingDetails(Bestelling bestelling) {
        List<BestellingDetails> bestellingDetails = new ArrayList<>();

        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL, Connectie.DATABASE_USERNAME, Connectie.DATABASE_PASSWORD);
             PreparedStatement query = conn.prepareStatement(SELECT_BESTELLING_DETAILS)) {
            String idOrder = bestelling.getIdOrder();
            query.setString(1, idOrder);
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    int idOrderDetails = rs.getInt("idOrderDetails");
                    double eenheidsPrijs = rs.getDouble("eenheidsPrijs");
                    int idProduct = rs.getInt("idProduct");
                    String productName = rs.getString("productName");
                    double btwtarief = rs.getDouble("btwtarief");
                    int productQuantity = rs.getInt("productQuantity");

                    BestellingDetails bestellingDetail = new BestellingDetails(idOrderDetails, eenheidsPrijs, idProduct, productName, btwtarief, productQuantity);
                    bestellingDetails.add(bestellingDetail);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        return bestellingDetails;
    }

}
