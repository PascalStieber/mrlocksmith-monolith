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
import javax.persistence.Query;
import javax.persistence.Subgraph;

import com.pascalstieber.mrlocksmith.adress.AdressEntity;
import com.pascalstieber.mrlocksmith.user.UserEntity;

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

    public List<OrderEntity> fetchAllOrders() {
	EntityGraph<OrderEntity> orderGraph = em.createEntityGraph(OrderEntity.class);
	// userEntityGrap.addAttributeNodes("id");
	Subgraph<UserEntity> userGraph = orderGraph.addSubgraph("user", UserEntity.class);
	Subgraph<AdressEntity> adressGraph = userGraph.addSubgraph("adresses", AdressEntity.class);
	
	Query q = em.createQuery("SELECT o FROM OrderEntity o");
	q.setHint("javax.persistence.fetchgraph", orderGraph);
	q.setHint("javax.persistence.loadgraph", orderGraph);
	List<OrderEntity> result = q.getResultList();
	return result;
    }


}
