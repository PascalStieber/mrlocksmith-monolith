package com.pascalstieber.mrlocksmith.item;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import org.hibernate.annotations.Cascade;
import org.hibernate.validator.constraints.NotEmpty;

import com.pascalstieber.mrlocksmith.common.AbstractMRLSEntity;
import com.pascalstieber.mrlocksmith.offer.OfferEntity;

@Entity
public class ItemEntity extends AbstractMRLSEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne(cascade=CascadeType.ALL)
    private OfferEntity offer;

    // Die Validierungen wurden in die GUI verlegt. Somit ist es m�glich, Items
    // anzulegen und zu persistieren um sie dann auf der Maske als "Leerzeile"
    // anzeigen lassen zu k�nnen. Durch die Hibernate Validerung w�re das so
    // nicht m�glich gewesen.
    private String name;
    private String value;

    public OfferEntity getOffer() {
	return offer;
    }

    public void setOffer(OfferEntity offer) {
	this.offer = offer;
    }

    public String getValue() {
	return value;
    }

    public void setValue(String value) {
	this.value = value;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

}
