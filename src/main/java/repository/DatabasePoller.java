package repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import domein.Bestelling;
import domein.DomeinController;
import domein.Interface_Bestelling;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class DatabasePoller {

    private static final long POLLING_INTERVAL_MS = 1000;
    private final int idLeverancier;
    private Timer timer;
	private ObservableList<Interface_Bestelling> CurrentBestellingList;

    public DatabasePoller(DomeinController controller,int idLeverancier) {
        this.idLeverancier = idLeverancier;
        controller.setPoller(this);
        this.CurrentBestellingList = FXCollections.observableArrayList();
    }

    public void startPolling( Stage primaryStage) {
        primaryStage.setOnCloseRequest(event -> stopPolling());
        System.out.println("started");
        
      
        timer = new Timer();
        timer.schedule(new PollingTask(), 0, POLLING_INTERVAL_MS);
    }

    public void stopPolling() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
    }

    private class PollingTask extends TimerTask {
        @Override
        public void run() {
        	
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("b2bDeleware");
            EntityManager entityManager = emf.createEntityManager();

            try {
                entityManager.getTransaction().begin();

                Query query = entityManager.createQuery("SELECT b FROM Bestelling b WHERE b.idLeverancier = :id", Bestelling.class)
                        .setParameter("id", idLeverancier);
                @SuppressWarnings("unchecked")
                List<Bestelling> updatedBestellingList = query.getResultList();


                for (Bestelling updatedBestelling : updatedBestellingList) {
                    Bestelling originalBestelling = findBestellingById(CurrentBestellingList, updatedBestelling.getIdOrder());

                    if (originalBestelling != null) {
                        String currentBetalingStatus = originalBestelling.getBetalingStatus();
                        String newBetalingStatus = updatedBestelling.getBetalingStatus();
                        if (!currentBetalingStatus.equals(newBetalingStatus)) {
                            System.out.println("Status changed!");
                            originalBestelling.updateBetalingStatus(true);
                        }
                    }
                }


                entityManager.getTransaction().commit();
            } catch (Exception e) {
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
                e.printStackTrace();
            } finally {
                entityManager.close();
                emf.close();
            }
        }
    }
    
    private Bestelling findBestellingById(List<Interface_Bestelling> bestellingList, String idOrder) {
        for (Interface_Bestelling bestelling : bestellingList) {
            if (bestelling.getIdOrder().equals(idOrder)) {
                if (bestelling instanceof Bestelling) {
                    return (Bestelling) bestelling;
                } else {
                	System.out.println("not an isntance");
                    return null;
                }
            }
        }
        return null; 
    }


    public void setCurrentList(ObservableList<Interface_Bestelling> bestellingenList) {
        if (this.CurrentBestellingList == null) {
            this.CurrentBestellingList = FXCollections.observableArrayList();
        }
        this.CurrentBestellingList.addAll(bestellingenList);
    }
}
