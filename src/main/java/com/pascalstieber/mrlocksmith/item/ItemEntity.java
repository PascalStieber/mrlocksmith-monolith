package com.pascalstieber.mrlocksmith.item;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;

import com.pascalstieber.mrlocksmith.common.AbstractMRLSEntity;
import com.pascalstieber.mrlocksmith.offer.OfferEntity;

@Entity
public class ItemEntity extends AbstractMRLSEntity {

    private static final long serialVersionUID = 1L;

    @ManyToOne
    private OfferEntity offer;

    @NotEmpty(message = "Vergeben Sie für den Posten einen Namen und einen Betrag.")
    private String name;
    // @TODO: hier muss ein Pattern zur Überprüfung der Währung erstellt werden.
    // Der Datentyp sollte geändert werden.
    @NotEmpty(message = "Vergeben Sie für den Posten einen Namen und einen Betrag.")
    @Pattern(regexp = "^[0-9]*[.][0-9][0-9]", message="Betrag muss im Format '00.00' sein")
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
