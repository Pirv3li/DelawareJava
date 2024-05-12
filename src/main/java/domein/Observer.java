package domein;

public interface Observer {
    void update(Interface_Bestelling bestelling);
    
    void update(Interface_Leverancier leverancier);
    
    void update(Interface_GoedKeuringLeverancier goedKeuringLeverancier);
}
