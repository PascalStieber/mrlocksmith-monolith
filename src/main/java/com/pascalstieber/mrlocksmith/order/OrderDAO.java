package com.pascalstieber.mrlocksmith.order;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.Subgraph;

import com.pascalstieber.mrlocksmith.adress.AdressEntity;
import com.pascalstieber.mrlocksmith.item.ItemEntity;
import com.pascalstieber.mrlocksmith.offer.OfferEntity;
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
	Subgraph<OfferEntity> offerGraph = orderGraph.addSubgraph("offers", OfferEntity.class);
	Subgraph<ItemEntity> itemGraph = offerGraph.addSubgraph("items", ItemEntity.class);

	// Das DISTINCT ist nötig, um keine redundanten Daten des orderGraphs
	// als result zu erhalten. Dieses Problem scheint seinen Ursprung in dem
	// tiefen EntityGraphen zu haben. Dazu gibt es bspw. auch ein JPA Jira
	// Ticket: https://java.net/jira/browse/JPA_SPEC-96
	Query q = em.createQuery("SELECT DISTINCT o FROM OrderEntity o GROUP BY o.id");
//	Query q = em.createQuery("SELECT o FROM OrderEntity o");
	q.setHint("javax.persistence.fetchgraph", orderGraph);
	q.setHint("javax.persistence.loadgraph", orderGraph);
	List<OrderEntity> result = q.getResultList();
	return result;
    }

    public OrderEntity fetchOrderByID(Long pOrderid) {
	OrderEntity order = em.find(OrderEntity.class, pOrderid);
	return order;
    }

}
