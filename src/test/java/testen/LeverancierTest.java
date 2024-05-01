package testen;
import org.junit.jupiter.api.Test;

import domein.Leverancier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LeverancierTest {

    @Test
    public void testGetIdLeverancier() {
        // Arrange
        Leverancier leverancier = new Leverancier("username", "password", "123" ,"method1, method2", "roles", 1);

        // Act
        int id = leverancier.getIdLeverancier();

        // Assert
        assertEquals(1, id);
    }

    @Test
    public void testGetLeveranciernummer() {
        // Arrange
        Leverancier leverancier = new Leverancier("username", "password", "123", "method1, method2", "roles", 1);

        // Act
        String nummer = leverancier.getLeveranciernummer();

        // Assert
        assertEquals("123", nummer);
    }

    @Test
    public void testGetBetaalMethodes() {
        // Arrange
        Leverancier leverancier = new Leverancier("username", "password", "123", "method1, method2", "roles", 1);

        // Act
        String methodes = leverancier.getBetaalMethodes();

        // Assert
        assertEquals("method1, method2", methodes);
    }

    @Test
    public void testGetEmail() {
        // Arrange
        Leverancier leverancier = new Leverancier("username", "password", "123", "method1, method2", "roles", 1, "test@email.com");

        // Act
        String email = leverancier.getEmail();

        // Assert
        assertEquals("test@email.com", email);
    }
}