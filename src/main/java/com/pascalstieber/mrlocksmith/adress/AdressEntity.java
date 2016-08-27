package com.pascalstieber.mrlocksmith.adress;

import javax.persistence.Entity;
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;

import org.hibernate.validator.constraints.NotEmpty;

import com.pascalstieber.mrlocksmith.common.AbstractMRLSEntity;

@Entity
public class AdressEntity extends AbstractMRLSEntity {

    private static final long serialVersionUID = 1L;

    @NotEmpty(message="PLZ ist ein erforderliches Feld!")
    @DecimalMax(value="999999")
    @DecimalMin(value="1111")
    private String postcode;
    
    @NotEmpty(message="Ort ist ein erforderliches Feld!")
    private String country;

    @NotEmpty(message="Straﬂe ist ein erforderliches Feld!")
    private String street;
    
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
