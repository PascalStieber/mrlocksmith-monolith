package com.pascalstieber.mrlocksmith.offer;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

@Stateless
public class OfferDAO {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "MrLocksmithDS")
    EntityManager em;

    public void saveNewOffer(OfferEntity pOffer) {
	em.persist(pOffer);
    }

    public void updateOffer(OfferEntity pOrder) {
	em.merge(pOrder);
    }

    public List<OfferEntity> fetchAllOffers() {
//	EntityGraph<OfferEntity> offerGraph = em.createEntityGraph(OfferEntity.class);
	Query q = em.createQuery("SELECT o FROM OfferEntity o");
	List<OfferEntity> result = q.getResultList();
	return result;
    }

    public OfferEntity fetchOfferByID(Long pOfferid) {
	OfferEntity offer = em.find(OfferEntity.class, pOfferid);
	return offer;
    }


}
