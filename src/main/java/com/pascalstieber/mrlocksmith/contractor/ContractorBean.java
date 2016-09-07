package com.pascalstieber.mrlocksmith.contractor;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pascalstieber.mrlocksmith.adress.AdressEntity;
import com.pascalstieber.mrlocksmith.user.UserDAO;
import com.pascalstieber.mrlocksmith.user.UserEntity;

@Named
@ViewScoped
@ManagedBean
public class ContractorBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private UserEntity user;
    private ContractorEntity contractor;
    private AdressEntity adress;
    private String customerOrContractor = "";

    @Inject
    private UserDAO userDAO;
    @Inject
    private ContractorDAO contractorDAO;

    @PostConstruct
    private void init() {
	adress = new AdressEntity();
	user = new UserEntity();
	contractor = new ContractorEntity();
    }

    public String newCustomerOrContractor(String pType) {
	if ("customer".equals(pType)) {
	    customerOrContractor = "customer";
	    return "./customerRegister.xhtml?faces-redirect=true";
	} else {
	    customerOrContractor = "contractor";
	    return "./contractorRegister.xhtml?faces-redirect=true";
	}

    }

    public String saveCustomer() {
	contractor.setRole("Customer");
	user.addAdress(adress);
	userDAO.saveNewUser(user);
	return "/faces/customer/showCustomersOffers.xhtml?faces-redirect=true";
    }

    public String saveContractor() {
	contractor.setRole("Contractor");
	contractor.addAdress(adress);
	contractorDAO.saveNewContractor(contractor);
	return "/faces/contractor/showAllOrders.xhtml?faces-redirect=true";
    }

    public UserEntity getUser() {
	return user;
    }

    public void setUser(UserEntity user) {
	this.user = user;
    }

    public ContractorEntity getContractor() {
	return contractor;
    }

    public void setContractor(ContractorEntity contractor) {
	this.contractor = contractor;
    }

    public AdressEntity getAdress() {
	return adress;
    }

    public void setAdress(AdressEntity adress) {
	this.adress = adress;
    }

    public String getCustomerOrContractor() {
	return customerOrContractor;
    }

    public void setCustomerOrContractor(String customerOrContractor) {
	this.customerOrContractor = customerOrContractor;
    }

}
