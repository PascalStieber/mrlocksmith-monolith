package com.pascalstieber.mrlocksmith.order;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityGraph;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;
import javax.persistence.Subgraph;
import javax.persistence.TypedQuery;

import com.pascalstieber.mrlocksmith.adress.AdressEntity;
import com.pascalstieber.mrlocksmith.item.ItemEntity;
import com.pascalstieber.mrlocksmith.offer.OfferEntity;
import com.pascalstieber.mrlocksmith.user.UserEntity;

@Stateless
public class OrderDAO {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "MrLocksmithDS")
    EntityManager em;

    public void saveNewOrder(OrderEntity pOrder) {
	pOrder.setCreatedAt(new Date());
	em.persist(pOrder);
    }

    public void updateOrder(OrderEntity pOrder) {
	em.merge(pOrder);
	em.flush();
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
	q.setHint("javax.persistence.fetchgraph", orderGraph);
	q.setHint("javax.persistence.loadgraph", orderGraph);
	List<OrderEntity> result = q.getResultList();
	return result;
    }
    
    
    public List<OrderEntity> fetchAllOrdersByUserid(UserEntity Userid) {
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
	Query q = em.createQuery("SELECT DISTINCT o FROM OrderEntity o WHERE o.user= :userid GROUP BY o.id");
	q.setParameter("userid", Userid);
	q.setHint("javax.persistence.fetchgraph", orderGraph);
	q.setHint("javax.persistence.loadgraph", orderGraph);
	List<OrderEntity> result = q.getResultList();
	return result;
    }

    public OrderEntity fetchOrderByID(Long pOrderid) {
	EntityGraph<OrderEntity> orderGraph = em.createEntityGraph(OrderEntity.class);
	Subgraph<OfferEntity> offerGraph = orderGraph.addSubgraph("offers", OfferEntity.class);
	Subgraph<ItemEntity> itemGraph = offerGraph.addSubgraph("items", ItemEntity.class);
	
	TypedQuery<OrderEntity> q = em.createQuery("SELECT DISTINCT o FROM OrderEntity o WHERE o.id = :orderid", OrderEntity.class);
	q.setHint("javax.persistence.fetchgraph", orderGraph);
	q.setHint("javax.persistence.loadgraph", orderGraph);
	q.setParameter("orderid", pOrderid);
	
	return q.getResultList().get(0);
    }

    public Long fetchOrderAmount() {
	Query query = em.createQuery(" SELECT COUNT(o) FROM OrderEntity o");
	Long results = (Long) query.getResultList().get(0);
	return results;
    }

}
