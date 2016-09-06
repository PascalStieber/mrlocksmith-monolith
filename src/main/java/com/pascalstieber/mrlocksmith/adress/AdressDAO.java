package com.pascalstieber.mrlocksmith.adress;

import java.util.Date;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

@Stateless
public class AdressDAO {

    @PersistenceContext(type = PersistenceContextType.TRANSACTION, unitName = "MrLocksmithDS")
    EntityManager em;

    public void saveNewAdress(AdressEntity pAdress){
	pAdress.setCreatedAt(new Date());
	em.persist(pAdress);
    }

    public void updateAdress(AdressEntity pAdress){
	em.merge(pAdress);
    }
    
}
