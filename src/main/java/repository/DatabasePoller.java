package repository;


import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import domein.Bestelling;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.Query;
import javafx.stage.Stage;

public class DatabasePoller {

    private static final long POLLING_INTERVAL_MS = 1000;
    private final int idLeverancier;
    private Timer timer;
    private List<Bestelling> originalBestellingList = new ArrayList<>();

    public DatabasePoller(int idLeverancier) {
        this.idLeverancier = idLeverancier;
    }

    public void startPolling(Stage primaryStage) {
        primaryStage.setOnCloseRequest(event -> stopPolling());
        System.out.println("started");
        populateOriginalBestellingList();
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
                    Bestelling originalBestelling = findBestellingById(originalBestellingList, updatedBestelling.getIdOrder());

                    boolean currentBetalingStatus = originalBestelling.getBetalingStatus();
                    boolean newBetalingStatus = updatedBestelling.getBetalingStatus();
                    if (currentBetalingStatus != newBetalingStatus) {
                        System.out.println("Status changed!");
                        originalBestelling.updateBetalingStatus(newBetalingStatus);
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
    
    private Bestelling findBestellingById(List<Bestelling> bestellingList, String idOrder) {
        for (Bestelling bestelling : bestellingList) {
            if (bestelling.getIdOrder().equals(idOrder)) {
                return bestelling;
            }
        }
        return null; 
    }
    
    private void populateOriginalBestellingList() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("b2bDeleware");
        EntityManager entityManager = emf.createEntityManager();

        try {
            entityManager.getTransaction().begin();

            // Query the database to get the initial list of Bestelling objects
            Query query = entityManager.createQuery("SELECT b FROM Bestelling b WHERE b.idLeverancier = :id", Bestelling.class)
                    .setParameter("id", idLeverancier);
            @SuppressWarnings("unchecked")
            List<Bestelling> initialBestellingList = query.getResultList();

            // Copy the initial list to the originalBestellingList
            originalBestellingList.addAll(initialBestellingList);

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
