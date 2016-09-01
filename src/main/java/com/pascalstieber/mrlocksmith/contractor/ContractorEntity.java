package com.pascalstieber.mrlocksmith.contractor;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.pascalstieber.mrlocksmith.adress.AdressEntity;
import com.pascalstieber.mrlocksmith.common.AbstractMRLSEntity;
import com.pascalstieber.mrlocksmith.offer.OfferEntity;

@Entity
public class ContractorEntity extends AbstractMRLSEntity {

    private static final long serialVersionUID = -4625542971441659206L;
    @NotEmpty(message="Vorname darf nicht leer sein!")
    @Size(min=2, max=20, message="Der Vorname muss min. 3 und darf max. 20 Zeichen lang sein.")
    private String firstname;
    @NotEmpty(message="Nachname darf nicht leer sein!")
    @Size(min=2, max=30, message="Der Nachname muss min. 3 und darf max. 30 Zeichen lang sein.")
    private String surname;
     
    @NotEmpty(message="Email darf nicht leer sein!")
    @Email
    private String email;
    @NotEmpty(message="Telefonnummer darf nicht leer sein!")
    private String phonenumber;
    
    @ManyToMany(cascade = CascadeType.ALL,targetEntity=AdressEntity.class)
    @JoinTable(name="Contractor_Adress")
    private Set<AdressEntity> adresses = new HashSet<>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="contractor", fetch = FetchType.LAZY)
    private Set<OfferEntity> offers = new HashSet<>();
    
    
    public Set<OfferEntity> getOffers() {
        return offers;
    }
    public void addOffer(OfferEntity offer) {
        this.offers.add(offer);
    }
    public Set<AdressEntity> getAdresses() {
        return adresses;
    }
    public void addAdress(AdressEntity adress) {
        this.adresses.add(adress);
    }
    public String getFirstname() {
        return firstname;
    }
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
	return email;
    }
    public void setEmail(String email) {
	this.email = email;
    }
    public String getPhonenumber() {
        return phonenumber;
    }
    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
   

    
}
