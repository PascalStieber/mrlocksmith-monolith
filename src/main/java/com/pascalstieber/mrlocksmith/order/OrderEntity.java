package com.pascalstieber.mrlocksmith.order;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.pascalstieber.mrlocksmith.adress.AdressEntity;
import com.pascalstieber.mrlocksmith.common.AbstractMRLSEntity;
import com.pascalstieber.mrlocksmith.offer.OfferEntity;
import com.pascalstieber.mrlocksmith.user.UserEntity;

@Entity
public class OrderEntity extends AbstractMRLSEntity {

    private static final long serialVersionUID = 1L;

    private boolean keyNotAvailable;
    private String homeOrCar = "";
    private String door = "";
    private boolean express;

    @ManyToOne
    private UserEntity user;

    @ManyToOne
    private AdressEntity adress;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "order", fetch = FetchType.LAZY)
    @OrderBy("id DESC")
    private Set<OfferEntity> offers = new HashSet<>();

    public Set<OfferEntity> getOffers() {
	return offers;
    }

    public void addOffer(OfferEntity offer) {
	this.offers.add(offer);
    }

    public UserEntity getUser() {
	return user;
    }

    public void setUser(UserEntity user) {
	this.user = user;
    }

    public boolean isKeyNotAvailable() {
	return keyNotAvailable;
    }

    public void setKeyNotAvailable(boolean keyNotAvailable) {
	this.keyNotAvailable = keyNotAvailable;
    }

    public String getHomeOrCar() {
	return homeOrCar;
    }

    public void setHomeOrCar(String homeOrCar) {
	this.homeOrCar = homeOrCar;
    }

    public String getDoor() {
	return door;
    }

    public void setDoor(String door) {
	this.door = door;
    }

    public boolean isExpress() {
	return express;
    }

    public void setExpress(boolean express) {
	this.express = express;
    }

    public AdressEntity getAdress() {
	return adress;
    }

    public void setAdress(AdressEntity adress) {
	this.adress = adress;
    }

}
