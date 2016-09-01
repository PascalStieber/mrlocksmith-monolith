package com.pascalstieber.mrlocksmith.adress;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotEmpty;

import com.pascalstieber.mrlocksmith.common.AbstractMRLSEntity;
import com.pascalstieber.mrlocksmith.order.OrderEntity;
import com.pascalstieber.mrlocksmith.user.UserEntity;

@Entity
public class AdressEntity extends AbstractMRLSEntity {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message="PLZ darf nicht leer sein!")
    @DecimalMax(value="999999")
    @DecimalMin(value="1111")
    private String postcode;
    
    @NotEmpty(message="Ort darf nicht leer sein!")
    private String country;

    @NotEmpty(message="Straﬂe darf nicht leer sein!")
    private String street;

    @NotEmpty(message="Hausnr. ist ein erforderliches Feld")
    private String streetnumber;
    
    @ManyToMany(mappedBy="adresses")
    private Set<UserEntity> user = new HashSet<UserEntity>();
   
    @OneToMany(cascade=CascadeType.ALL, mappedBy="adress", fetch = FetchType.LAZY)
    private Set<OrderEntity> orders = new HashSet<>();
    
    public Set<OrderEntity> getOrders() {
        return orders;
    }

    public void addOrder(OrderEntity order) {
        this.orders.add(order);
    }

    public Set<UserEntity> getUser() {
        return user;
    }

    public void addUser(UserEntity user) {
        this.user.add(user);
    }

    public String getStreet() {
        return street;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetnumber() {
	return streetnumber;
    }

    public void setStreetnumber(String streetnumber) {
	this.streetnumber = streetnumber;
    }



}
