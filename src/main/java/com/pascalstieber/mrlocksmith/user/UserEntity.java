package com.pascalstieber.mrlocksmith.user;

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
import com.pascalstieber.mrlocksmith.order.OrderEntity;

@Entity
public class UserEntity extends AbstractMRLSEntity {

    private static final long serialVersionUID = -4625542971441659206L;
    @NotEmpty
    @Size(min=2, max=20, message="Der Vorname muss min. 3 und darf max. 20 Zeichen lang sein.")
    private String firstname;
    @NotEmpty
    @Size(min=2, max=30, message="Der Nachname muss min. 3 und darf max. 30 Zeichen lang sein.")
    private String surname;
     
    @NotEmpty(message="Email ist ein erforderliches Feld!")
    @Email
    private String email;
    
    @ManyToMany(cascade = CascadeType.ALL,targetEntity=AdressEntity.class)
    @JoinTable(name="User_Adress")
    private Set<AdressEntity> adresses = new HashSet<>();
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="user", fetch = FetchType.LAZY)
    private Set<OrderEntity> order = new HashSet<>();
    
    
    public Set<OrderEntity> getOrder() {
        return order;
    }
    public void addOrder(OrderEntity order) {
        this.order.add(order);
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
   

    
}
