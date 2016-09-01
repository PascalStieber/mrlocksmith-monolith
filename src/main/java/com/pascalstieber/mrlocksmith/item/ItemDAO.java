package com.pascalstieber.mrlocksmith.item;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

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

    


}
