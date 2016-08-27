package com.pascalstieber.mrlocksmith.user;

import javax.persistence.Entity;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import com.pascalstieber.mrlocksmith.common.AbstractMRLSEntity;

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
