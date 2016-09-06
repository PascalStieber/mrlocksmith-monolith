package com.pascalstieber.mrlocksmith.user;

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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import com.pascalstieber.mrlocksmith.adress.AdressEntity;

@Stateless
public class UserDAO {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "MrLocksmithDS")
    EntityManager em;

    public void saveNewUser(UserEntity pUser) {
	pUser.setCreatedAt(new Date());
	em.persist(pUser);
    }

    public void updateUser(UserEntity pUser) {
	em.merge(pUser);
    }

    public List<UserEntity> fetchAllUsers() {
	EntityGraph<UserEntity> userEntityGraph = em.createEntityGraph(UserEntity.class);
	// userEntityGrap.addAttributeNodes("id");
	Subgraph<AdressEntity> adressGraph = userEntityGraph.addSubgraph("adresses", AdressEntity.class);
	// adressGraph.addAttributeNodes("name");

	Query q = em.createQuery("SELECT u FROM UserEntity u");
	q.setHint("javax.persistence.fetchgraph", userEntityGraph);
	q.setHint("javax.persistence.loadedgraph", userEntityGraph);
	List<UserEntity> result = q.getResultList();
	return result;
    }

    public UserEntity fetchUserByEmail(String pEmail) {
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<UserEntity> cq = cb.createQuery(UserEntity.class);
	Root<UserEntity> userRoot = cq.from(UserEntity.class);
	cq.select(userRoot).where(cb.equal(userRoot.get("email"), pEmail));
	
	TypedQuery<UserEntity> query = em.createQuery(cq);
	List<UserEntity> results = query.getResultList();
	return results.get(0);
    }

}
