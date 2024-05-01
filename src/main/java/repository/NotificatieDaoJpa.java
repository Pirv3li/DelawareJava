package repository;

import domein.Notificatie;
import domein.Bestelling;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;
import jakarta.persistence.criteria.Predicate;
import java.util.Date;

import jakarta.persistence.EntityTransaction;
import jakarta.persistence.Query;

import java.util.List;

public class NotificatieDaoJpa extends GenericDaoJpa<Notificatie> {

    public NotificatieDaoJpa() {
        super(Notificatie.class);
    }

    public void createNotification(String text, String onderwerp, boolean geopend, Date datum, Bestelling bestelling) {
    	EntityTransaction transaction = em.getTransaction();
    	String idOrder = bestelling.getIdOrder();
    	try {
    		transaction.begin();
    		
    		Notificatie notificatie = new Notificatie(text, onderwerp, geopend, datum, idOrder);
            em.persist(notificatie);

            transaction.commit();
    		
    	} catch (Exception ex) {
    		if (transaction != null && transaction.isActive()) {
    			transaction.rollback();
    		}
    		throw ex;
    	}
    }

    public List<Notificatie> getAllNotificationsByLeverancierId(int idLeverancier, int begin, int aantal) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Notificatie> cq = cb.createQuery(Notificatie.class);
        Root<Notificatie> notificatieRoot = cq.from(Notificatie.class);
        Join<Notificatie, Bestelling> orderJoin = notificatieRoot.join("bestelling");

        cq.where(cb.equal(orderJoin.get("idLeverancier"), idLeverancier));
        cq.orderBy(cb.asc(notificatieRoot.get("geopend")), cb.desc(notificatieRoot.get("datum")));

        return em.createQuery(cq)
                .setFirstResult(begin - 1)
                .setMaxResults(aantal)
                .getResultList();
    }

    public long countUnopenedNotificationsByLeverancierId(int idLeverancier) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Long> cq = cb.createQuery(Long.class);
        Root<Notificatie> notificatieRoot = cq.from(Notificatie.class);
        Join<Notificatie, Bestelling> orderJoin = notificatieRoot.join("bestelling");
    
        Predicate leverancierPredicate = cb.equal(orderJoin.get("idLeverancier"), idLeverancier);
        Predicate geopendPredicate = cb.isFalse(notificatieRoot.get("geopend"));
        cq.where(cb.and(leverancierPredicate, geopendPredicate));
    
        cq.select(cb.count(notificatieRoot.get("idNotificatie")));
    
        return em.createQuery(cq).getSingleResult();
    }
}