package testen;

import org.junit.jupiter.api.Test;

import domein.Admin;
import domein.Adres;
import domein.App;
import domein.Bedrijf;
import domein.Bestelling;
import domein.BestellingDetails;
import domein.DomeinController;
import domein.Leverancier;
import domein.Product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class DomeinControllerTest {

    @Test
    public void testAanmeldenAdmin() {
        App mockApp = mock(App.class);
        Admin mockAdmin = mock(Admin.class);
        when(mockApp.AanmeldenAdmin("username", "password")).thenReturn(mockAdmin);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        boolean aangemeld = controller.AanmeldenAdmin("username", "password");

        assertEquals(true, aangemeld);
    }

    @Test
    public void testAanmelden() {
        App mockApp = mock(App.class);
        Leverancier mockLeverancier = mock(Leverancier.class);
        when(mockApp.Aanmelden("username", "password")).thenReturn(mockLeverancier);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        boolean aangemeld = controller.Aanmelden("username", "password");

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
        App mockApp = mock(App.class);
        Leverancier mockLeverancier = mock(Leverancier.class);
        List<Bestelling> mockBestellingen = new ArrayList<>();
        when(mockApp.getBestellingenByLeverancierId(mockLeverancier)).thenReturn(mockBestellingen);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);
        controller.setLeverancier(mockLeverancier);

        List<Bestelling> bestellingen = controller.FindbestellingenByLeverancier();

        assertEquals(mockBestellingen, bestellingen);
    }

    @Test
    public void testGetBestellingDetails() {
        App mockApp = mock(App.class);
        Bestelling mockBestelling = mock(Bestelling.class);
        List<BestellingDetails> mockBestellingDetails = new ArrayList<>();
        when(mockApp.getBestellingDetails(mockBestelling)).thenReturn(mockBestellingDetails);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        List<BestellingDetails> bestellingDetails = controller.getBestellingDetails(mockBestelling);

        assertEquals(mockBestellingDetails, bestellingDetails);
    }

    @Test
    public void testGetProductByProductId() {
        App mockApp = mock(App.class);
        BestellingDetails mockBestellingDetail = mock(BestellingDetails.class);
        Product mockProduct = mock(Product.class);
        when(mockApp.getProductByProductId(mockBestellingDetail.getIdProduct())).thenReturn(mockProduct);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        Product product = controller.getProductByProductId(mockBestellingDetail);

        assertEquals(mockProduct, product);
    }

    @Test
    public void testGetBedrijven() {
        App mockApp = mock(App.class);
        List<Bedrijf> mockBedrijven = new ArrayList<>();
        when(mockApp.getBedrijven()).thenReturn(mockBedrijven);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        List<Bedrijf> bedrijven = controller.getBedrijven();

        assertEquals(mockBedrijven, bedrijven);
    }

    @Test
    public void testGetLeverancierGegevensByIdBedrijf() {
        App mockApp = mock(App.class);
        Leverancier mockLeverancier = mock(Leverancier.class);
        when(mockApp.getLeverancierGegevensByIdBedrijf(1)).thenReturn(mockLeverancier);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        Leverancier leverancier = controller.getLeverancierGegevensByIdBedrijf(1);

        assertEquals(mockLeverancier, leverancier);
    }

    @Test
    public void testGetAdresByIdAdres() {
        App mockApp = mock(App.class);
        Adres mockAdres = mock(Adres.class);
        when(mockApp.getAdresByIdAdres(1)).thenReturn(mockAdres);

        DomeinController controller = new DomeinController();
        controller.setApp(mockApp);

        Adres adres = controller.getAdresByIdAdres(1);

        assertEquals(mockAdres, adres);
    }
}