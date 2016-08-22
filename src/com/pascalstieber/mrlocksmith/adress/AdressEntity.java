package com.pascalstieber.mrlocksmith.adress;

public class AdressEntity {

    private String Postcode;
    private String Country;
    private String Street;
    private String StreetNumber;
    
    public String getPostcode() {
        return Postcode;
    }
    public void setPostcode(String postcode) {
        Postcode = postcode;
    }
    public String getCountry() {
        return Country;
    }
    public void setCountry(String country) {
        Country = country;
    }
    public String getStreet() {
        return Street;
    }
    public void setStreet(String street) {
        Street = street;
    }
    public String getStreetNumber() {
        return StreetNumber;
    }
    public void setStreetNumber(String streetNumber) {
        StreetNumber = streetNumber;
    }
    
}
