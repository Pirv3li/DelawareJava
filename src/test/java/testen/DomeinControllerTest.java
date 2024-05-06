package testen;

import org.junit.jupiter.api.Test;

import domein.Admin;
import domein.Adres;
import domein.B2B_Portal;
import domein.Bedrijf;
import domein.Bestelling;
import domein.BestellingDetails;
import domein.DomeinController;
import domein.Interface_Adres;
import domein.Interface_Bedrijf;
import domein.Interface_Bestelling;
import domein.Interface_BestellingDetails;
import domein.Interface_Leverancier;
import domein.Interface_Product;
import domein.Leverancier;
import domein.Product;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class DomeinControllerTest {

    @Test
    public void testAanmeldenAdmin() {
        B2B_Portal mockApp = mock(B2B_Portal.class);
        Admin mockAdmin = mock(Admin.class);
        when(mockApp.aanmeldenAdmin("username", "password")).thenReturn(mockAdmin);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        boolean aangemeld = controller.aanmeldenAdmin("username", "password");

        assertEquals(true, aangemeld);
    }

    @Test
    public void testAanmelden() {
        B2B_Portal mockApp = mock(B2B_Portal.class);
        Leverancier mockLeverancier = mock(Leverancier.class);
        when(mockApp.aanmelden("username", "password")).thenReturn(mockLeverancier);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        boolean aangemeld = controller.aanmelden("username", "password");

        assertEquals(true, aangemeld);
    }

    @Test
    public void testUitloggen() {
        DomeinController controller = new DomeinController();

        controller.uitloggen();

        assertNull(controller.getAdmin());
        assertNull(controller.getLeverancier());
    }

    @Test
    public void testFindbestellingenByLeverancier() {
        B2B_Portal mockApp = mock(B2B_Portal.class);
        Leverancier mockLeverancier = mock(Leverancier.class);
        ObservableList<Interface_Bestelling> mockBestellingen = FXCollections.observableArrayList();
        when(mockApp.getBestellingenByLeverancierId(mockLeverancier)).thenReturn(mockBestellingen);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);
        controller.setLeverancier(mockLeverancier);

        List<Interface_Bestelling> bestellingen = controller.findBestellingenByLeverancier();

        assertEquals(mockBestellingen, bestellingen);
    }

    @Test
    public void testGetBestellingDetails() {
        B2B_Portal mockApp = mock(B2B_Portal.class);
        Interface_Bestelling mockBestelling = mock(Bestelling.class);
        ObservableList<Interface_BestellingDetails> mockBestellingDetails = FXCollections.observableArrayList();
        when(mockApp.getBestellingDetails(mockBestelling)).thenReturn(mockBestellingDetails);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        List<Interface_BestellingDetails> bestellingDetails = controller.getBestellingDetails(mockBestelling);

        assertEquals(mockBestellingDetails, bestellingDetails);
    }

    @Test
    public void testGetProductByProductId() {
        B2B_Portal mockApp = mock(B2B_Portal.class);
        Interface_BestellingDetails mockBestellingDetail = mock(BestellingDetails.class);
        Product mockProduct = mock(Product.class);
        when(mockApp.getProductByProductId(mockBestellingDetail.getIdProduct())).thenReturn(mockProduct);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        Interface_Product product = controller.getProductByProductId(mockBestellingDetail);

        assertEquals(mockProduct, product);
    }

    @Test
    public void testGetBedrijven() {
        B2B_Portal mockApp = mock(B2B_Portal.class);
        ObservableList<Interface_Bedrijf> mockBedrijven = FXCollections.observableArrayList();
        when(mockApp.getBedrijven()).thenReturn(mockBedrijven);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        List<Interface_Bedrijf> bedrijven = controller.getBedrijven();

        assertEquals(mockBedrijven, bedrijven);
    }

    @Test
    public void testGetLeverancierGegevensByIdBedrijf() {
        B2B_Portal mockApp = mock(B2B_Portal.class);
        Leverancier mockLeverancier = mock(Leverancier.class);
        when(mockApp.getLeverancierGegevensByIdBedrijf(1)).thenReturn(mockLeverancier);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        Interface_Leverancier leverancier = controller.getLeverancierGegevensByIdBedrijf(1);

        assertEquals(mockLeverancier, leverancier);
    }

    @Test
    public void testGetAdresByIdAdres() {
        B2B_Portal mockApp = mock(B2B_Portal.class);
        Adres mockAdres = mock(Adres.class);
        when(mockApp.getAdresByIdAdres(1)).thenReturn(mockAdres);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        Interface_Adres adres = controller.getAdresByIdAdres(1);

        assertEquals(mockAdres, adres);
    }
}