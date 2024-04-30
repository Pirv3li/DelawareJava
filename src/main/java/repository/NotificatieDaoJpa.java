package repository;

import domein.Notificatie;
import domein.Bestelling;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Root;

import java.util.List;

public class NotificatieDaoJpa extends GenericDaoJpa<Notificatie> {

    public NotificatieDaoJpa() {
        super(Notificatie.class);
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
}