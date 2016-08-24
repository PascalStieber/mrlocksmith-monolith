package com.pascalstieber.mrlocksmith.user;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class UserEntity {

    @Id
    @GeneratedValue
    private long id;
    
    private String Firstname;
    private String Surname;
    private String Email;
    
    
    public String getEmail() {
	return Email;
    }
    public void setEmail(String email) {
	Email = email;
    }
    public String getSurname() {
	return Surname;
    }
    public void setSurname(String surname) {
	Surname = surname;
    }
    public String getFirstname() {
	return Firstname;
    }
    public void setFirstname(String firstname) {
	Firstname = firstname;
    }

    
}
