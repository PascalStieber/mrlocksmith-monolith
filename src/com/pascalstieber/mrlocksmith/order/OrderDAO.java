package com.pascalstieber.mrlocksmith.order;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
public class OrderDAO {
    
    @Inject
    EntityManager em;
    
    public void saveNewOrder(OrderEntity pOrder){
//	em.persist(pOrder);
    }
    
}
