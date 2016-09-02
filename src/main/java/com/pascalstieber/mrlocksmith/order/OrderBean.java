package com.pascalstieber.mrlocksmith.order;

import java.io.Serializable;
import java.security.Principal;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.SessionContext;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pascalstieber.mrlocksmith.adress.AdressDAO;
import com.pascalstieber.mrlocksmith.adress.AdressEntity;
import com.pascalstieber.mrlocksmith.user.UserDAO;
import com.pascalstieber.mrlocksmith.user.UserEntity;

@Named
@ViewScoped
@ManagedBean
public class OrderBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private UserEntity user;
    private AdressEntity adress;
    private OrderEntity order;
    private Long orderID;

    private List<OrderEntity> allOrders;

    @Inject
    private OrderDAO orderDAO;
    @Inject
    private AdressDAO adressDAO;
    @Inject
    private UserDAO userDAO;

    @PostConstruct
    public void init() {
	setUser(new UserEntity());
	setAdress(new AdressEntity());
	setOrder(new OrderEntity());
	allOrders = orderDAO.fetchAllOrders();
//	scheiße, das war ja einfach :-D
	Principal principal = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
	System.out.println(principal.toString());
    }

    public void fetchSelectedOrder() {
	if (orderID != null) {
	    order = orderDAO.fetchOrderByID(orderID);
	    System.out.println("fetched with " + orderID);
	}
    }

    public String saveOrder() {
	// @TODO: dieser ganze krampf ist vllt. gar nicht nötig, würde man die
	// cascadierung richtig benutzen...
	orderDAO.saveNewOrder(order);
	adressDAO.saveNewAdress(adress);
	userDAO.saveNewUser(user);

	// füllen der n:m Beziehungstabelle
	adress.addUser(user);
	user.addAdress(adress);

	// order an user anhängen
	user.addOrder(order);
	order.setUser(user);

	// order an adresse anhängen
	order.setAdress(adress);
	adress.addOrder(order);

	// änderungen persistieren
	userDAO.updateUser(user);
	adressDAO.updateAdress(adress);
	orderDAO.updateOrder(order);

	return "/faces/customer/showCustomersOffers.xhtml?faces-redirect=true";
    }

    public String getHomeOrCarLink() {
	if ("home".equals(order.getHomeOrCar())) {
	    return "./questionnaireHome2.xhtml?faces-redirect=true";
	} else {
	    return "./questionnaireCar2.xhtml?faces-redirect=true";
	}
    }

    public void changeExpress() {
	if (order.isExpress()) {
	    order.setExpress(false);
	} else {
	    order.setExpress(true);
	}
    }

    public UserEntity getUser() {
	return user;
    }

    public void setUser(UserEntity user) {
	this.user = user;
    }

    public AdressEntity getAdress() {
	return adress;
    }

    public void setAdress(AdressEntity adress) {
	this.adress = adress;
    }

    public OrderEntity getOrder() {
	return order;
    }

    public void setOrder(OrderEntity order) {
	this.order = order;
    }

    public List<OrderEntity> getAllOrders() {
	return allOrders;
    }

    public void setAllOrders(List<OrderEntity> allOrders) {
	this.allOrders = allOrders;
    }

    public Long getOrderID() {
	return orderID;
    }

    public void setOrderID(Long orderID) {
	this.orderID = orderID;
    }

}
