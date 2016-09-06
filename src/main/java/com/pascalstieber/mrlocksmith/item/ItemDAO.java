package com.pascalstieber.mrlocksmith.item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

import com.pascalstieber.mrlocksmith.offer.OfferEntity;

@Stateless
public class ItemDAO {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "MrLocksmithDS")
    EntityManager em;

    public void saveNewItem(ItemEntity pItem) {
	em.persist(pItem);
    }

    public void updateItem(ItemEntity pItem) {
	em.merge(pItem);
    }

    public void deleteItem(ItemEntity pItem){
	em.remove(pItem);
    }
    
    public ItemEntity fetchItemByID(Long pItem) {
	ItemEntity item = em.find(ItemEntity.class, pItem);
	return item;
    }


}
