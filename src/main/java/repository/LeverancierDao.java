package repository;

import java.util.Date;
import java.util.List;

import domein.Bedrijf;
import domein.Bestelling;
import domein.BestellingDetails;
import domein.Interface_Bedrijf;
import domein.Interface_Bestelling;
import domein.Interface_Klant;
import domein.Interface_Leverancier;
import domein.Klant;
import domein.Leverancier;
import domein.Product;
import jakarta.persistence.EntityNotFoundException;
import javafx.collections.ObservableList;

public interface LeverancierDao extends GenericDao<Leverancier>{
	
	// LeverancierDAO
	
	public Leverancier getLeverancierByGebruikersnaam(String gebruikersnaam) throws EntityNotFoundException;

	public Leverancier getLeverancierByIdBedrijf(int idBedrijf) throws EntityNotFoundException;
	
	public Leverancier getLeverancierById(int idLeverancier) throws EntityNotFoundException;


	public void updateLeverancier(Interface_Leverancier lever) throws EntityNotFoundException;  
	
	public void updateLeverancierById(int idLeverancier, String gebruikersnaam, String email) throws EntityNotFoundException;  

    public void updateBedrijfByLeverancier(Leverancier leverancier, Bedrijf bedrijf) throws EntityNotFoundException;
    
    // BedrijfDAO
    
    public Interface_Bedrijf getBedrijfById(int id) throws EntityNotFoundException;
    
    public Interface_Bedrijf getBedrijfByKlantId(int id) throws EntityNotFoundException;
    
    // BestellingDao
    
    public List<Bestelling> getBestellingenByLeverancierId(int id) throws EntityNotFoundException;   

    public ObservableList<Bestelling> getBestellingenByKlantId(int id) throws EntityNotFoundException;   

    public void veranderBetalingStatus(String id, Boolean betalingStatus) throws EntityNotFoundException;  
    
    
    //BestellingDetailsDao
    
    public List<BestellingDetails> getBestellingDetailsByOrderId(String id) throws EntityNotFoundException;   
    
    //KlantDao
    
    List<Klant> getKlantenByLeverancierID(int leverancierId) throws EntityNotFoundException;
    
    Interface_Klant getKlantById(int klantId) throws EntityNotFoundException;
    
    //productDAO
    
    public Product getProductByProductId(int id) throws EntityNotFoundException;

    //notificatieDAO
    
    public void createNotificatie(String string, String string2, boolean b, Date date, Interface_Bestelling bestelling)throws EntityNotFoundException;
}
