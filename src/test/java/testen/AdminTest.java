package testen;
import org.junit.jupiter.api.Test;

import domein.Admin;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminTest {

    @Test
    public void testGetIdAdmin() {
        // Arrange
        Admin admin = new Admin("username", "password", 1, "roles", "test@email.com");

        // Act
        int id = admin.getIdAdmin();

        // Assert
        assertEquals(1, id);
    }

    @Test
    public void testGetRoles() {
        // Arrange
        Admin admin = new Admin("username", "password", 1, "roles", "test@email.com");

        // Act
        String roles = admin.getRoles();

        // Assert
        assertEquals("roles", roles);
    }
}