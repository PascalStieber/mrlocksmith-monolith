package com.pascalstieber.mrlocksmith.contractor;

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
public class ContractorDAO {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "MrLocksmithDS")
    EntityManager em;

    public void saveNewContractor(ContractorEntity pContractor) {
	pContractor.setCreatedAt(new Date());
	em.persist(pContractor);
    }

    public void updateContractor(ContractorEntity pContractor) {
	em.merge(pContractor);
    }

    public List<ContractorEntity> fetchAllContractor() {
	EntityGraph<ContractorEntity> contractorEntityGraph = em.createEntityGraph(ContractorEntity.class);
	Subgraph<AdressEntity> adressGraph = contractorEntityGraph.addSubgraph("adresses", AdressEntity.class);

	Query q = em.createQuery("SELECT u FROM UserEntity u");
	q.setHint("javax.persistence.fetchgraph", contractorEntityGraph);
	q.setHint("javax.persistence.loadedgraph", contractorEntityGraph);
	List<ContractorEntity> result = q.getResultList();
	return result;
    }

    public ContractorEntity fetchContractorByEmail(String pEmail) {
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<ContractorEntity> cq = cb.createQuery(ContractorEntity.class);
	Root<ContractorEntity> contractorRoot = cq.from(ContractorEntity.class);
	cq.select(contractorRoot).where(cb.equal(contractorRoot.get("email"), pEmail));
	TypedQuery<ContractorEntity> query = em.createQuery(cq);
	List<ContractorEntity> results = query.getResultList();
	return results.get(0);
    }

}
