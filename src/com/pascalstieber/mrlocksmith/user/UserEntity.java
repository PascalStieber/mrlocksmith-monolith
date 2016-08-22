package com.pascalstieber.mrlocksmith.user;

public class UserEntity {

    
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
