package com.pascalstieber.mrlocksmith.order;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Statelesss
public class OrderDAO {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "MrLocksmithDS")
    EntityManager em;

    public void saveNewOrder(OrderEntity pOrder){
	em.persist(pOrder);
    }

}
