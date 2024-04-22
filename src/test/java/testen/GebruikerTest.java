package testen;

import org.junit.jupiter.api.Test;

import domein.Gebruiker;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GebruikerTest {

    @Test
    public void testGetGebruikersnaam() {
        // Arrange
        Gebruiker gebruiker = new Gebruiker("username", "password");

        // Act
        String username = gebruiker.getGebruikersnaam();

        // Assert
        assertEquals("username", username);
    }

    @Test
    public void testGetPassword_Hash() {
        // Arrange
        Gebruiker gebruiker = new Gebruiker("username", "password");

        // Act
        String password = gebruiker.getPassword_Hash();

        // Assert
        assertEquals("password", password);
    }
}