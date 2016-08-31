package com.pascalstieber.mrlocksmith.adress;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotEmpty;

import com.pascalstieber.mrlocksmith.common.AbstractMRLSEntity;
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
    
    @ManyToMany(mappedBy="adresses")
    private Set<UserEntity> user = new HashSet<UserEntity>();
   
    public Set<UserEntity> getUser() {
        return user;
    }

    public void addUser(UserEntity user) {
        this.user.add(user);
    }

    private String streetNumber;
    
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

    public String getStreetNumber() {
	return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
	this.streetNumber = streetNumber;
    }



}
