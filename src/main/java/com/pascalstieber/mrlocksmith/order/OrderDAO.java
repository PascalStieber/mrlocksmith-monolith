package com.pascalstieber.mrlocksmith.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateless
public class OrderDAO {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "MrLocksmithDS")
    EntityManager em;

    public void saveNewOrder(OrderEntity pOrder) {
	em.persist(pOrder);
    }

    public void updateOrder(OrderEntity pOrder) {
	em.merge(pOrder);
    }

    public OrderEntity fetchOrder(OrderEntity pOrder) {
	EntityGraph<OrderEntity> entityGraph = em.createEntityGraph(OrderEntity.class);
	entityGraph.addAttributeNodes("addresses");

	Map<String, Object> hints = new HashMap<String, Object>();
	hints.put("javax.persistence.fetchgraph", entityGraph);
	OrderEntity order = em.find(OrderEntity.class, pOrder.getId(), hints);

	EntityManagerFactory factory = em.getEntityManagerFactory();
	factory.addNamedEntityGraph("user.adresses", entityGraph);	
	return order;
    }
    
    
    public List<OrderEntity> fetchAllOrders() {
//	EntityGraph<OrderEntity> entityGraph = em.createEntityGraph(OrderEntity.class);
//	entityGraph.addAttributeNodes("addresses");
//	
//	Map<String, Object> hints = new HashMap<String, Object>();
//	hints.put("javax.persistence.fetchgraph", entityGraph);
//	OrderEntity order = em.find(OrderEntity.class, pOrder.getId(), hints);
//
//	EntityManagerFactory factory = em.getEntityManagerFactory();
//	factory.addNamedEntityGraph("user.adresses", entityGraph);	
	return null;
    }
}
