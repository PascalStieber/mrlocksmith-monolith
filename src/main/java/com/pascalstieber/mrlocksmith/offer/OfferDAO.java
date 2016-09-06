package com.pascalstieber.mrlocksmith.offer;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.criterion.Order;

import com.pascalstieber.mrlocksmith.user.UserEntity;

@Stateless
public class OfferDAO {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "MrLocksmithDS")
    EntityManager em;

    public void saveNewOffer(OfferEntity pOffer) {
	pOffer.setCreatedAt(new Date());
	em.persist(pOffer);
    }

    public void updateOffer(OfferEntity pOffer) {
	em.merge(pOffer);
	em.flush();
    }

    public void deleteOffer(OfferEntity pOffer) {
	pOffer = em.find(OfferEntity.class, pOffer.getId());
	em.remove(pOffer);
    }

    public List<OfferEntity> fetchAllOffers() {
	// EntityGraph<OfferEntity> offerGraph =
	// em.createEntityGraph(OfferEntity.class);
	Query q = em.createQuery("SELECT o FROM OfferEntity o");
	List<OfferEntity> result = q.getResultList();
	return result;
    }

    public OfferEntity fetchOfferByID(Long pOfferid) {
	OfferEntity offer = em.find(OfferEntity.class, pOfferid);
	return offer;
    }

    public Timestamp fetchLastContract() {
	// CriteriaBuilder cb = em.getCriteriaBuilder();
	// CriteriaQuery<Date> cq = cb.createQuery(Date.class);
	// Root<OfferEntity> offerRoot = cq.from(OfferEntity.class);
	// cq.select(offerRoot).where(cb.greatest(offerRoot.get("acceptedAt")));
	// cq.multiselect(cb.max(offerRoot.get("acceptedAt")));
	// cq.select(offerRoot.get("acceptedAt")).orderBy(cb.asc(offerRoot.<Date>
	// get("acceptedAt")));
	// Query query = em.createQuery("SELECT o FROM OfferEntity o ORDER BY
	// o.acceptedAt DESC");
	Query query = em.createQuery(" SELECT MAX(o.acceptedAt) FROM OfferEntity o ");
	Timestamp results = (Timestamp) query.getResultList().get(0);
	return results;
    }

    public Long fetchOfferAmount() {
	Query query = em.createQuery(" SELECT COUNT(o) FROM OfferEntity o ");
	Long results = (Long) query.getResultList().get(0);
	return results;
    }

    public Long fetchAmountAcceptedOffers() {
	Query query = em.createQuery(" SELECT COUNT(o) FROM OfferEntity o WHERE o.accepted = TRUE");
	Long results = (Long) query.getResultList().get(0);
	return results;
    }

}
