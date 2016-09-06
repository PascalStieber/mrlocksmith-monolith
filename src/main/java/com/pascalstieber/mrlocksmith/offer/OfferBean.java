package com.pascalstieber.mrlocksmith.offer;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.jboss.security.auth.spi.Users.User;

import com.pascalstieber.mrlocksmith.contractor.ContractorDAO;
import com.pascalstieber.mrlocksmith.contractor.ContractorEntity;
import com.pascalstieber.mrlocksmith.item.ItemDAO;
import com.pascalstieber.mrlocksmith.item.ItemEntity;
import com.pascalstieber.mrlocksmith.order.OrderDAO;
import com.pascalstieber.mrlocksmith.order.OrderEntity;
import com.pascalstieber.mrlocksmith.user.UserDAO;
import com.pascalstieber.mrlocksmith.user.UserEntity;

@Named
@ViewScoped
@ManagedBean
public class OfferBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<OrderEntity> allOrders;
    private Long orderID;
    private OrderEntity order;
    private OfferEntity offer;
    private String loggedInUser;

    @Inject
    private OrderDAO orderDAO;
    @Inject
    private OfferDAO offerDAO;
    @Inject
    private ItemDAO itemDAO;
    @Inject
    private UserDAO userDAO;
    @Inject
    private ContractorDAO contractorDAO;

    @PostConstruct
    private void init() {
	setOffer(new OfferEntity());
	loggedInUser = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString();
    }

    public void addItemToOffer() {
	ItemEntity item = new ItemEntity();
	item.setOffer(offer);
	offer.setOrder(order);
	offer.addItem(item);
	// gibt es das offer bereits in der datenbank, wird es aktualisiert
	if (offer.getId() != null) {
	    offerDAO.updateOffer(offer);
	}
    }

    public void removeItemFromOffer(ItemEntity pItem) {
	offer.removeItem(pItem);
	// gibt es das item bereits in der datenbank dann entferne es bzw.
	// update das offer
	if (pItem.getId() != null) {
	    offerDAO.updateOffer(offer);
	}
    }

    public String updateOffer() {
	offerDAO.updateOffer(offer);
	return "/faces/contractor/showAllOrders.xhtml";

    }

    public void fetchSelectedOrder() {
	if (orderID != null) {
	    order = orderDAO.fetchOrderByID(orderID);
	    System.out.println("fetched with " + orderID);
	}
    }

    public String saveOffer() {
	ContractorEntity contractor = contractorDAO.fetchContractorByEmail(loggedInUser);
	offer.setContractor(contractor);
	offerDAO.saveNewOffer(offer);
	return "showAllOrders.xhtml?faces-redirect=true";
    }

    public void deleteOffer(OrderEntity pOrder) {
	if (getSubmittedTenderFromOrder(pOrder) != null) {
	    offerDAO.deleteOffer(getSubmittedTenderFromOrder(pOrder));
	    System.out.println("GELÖSCHT");
	}
    }

    private OfferEntity getSubmittedTenderFromOrder(OrderEntity pOrder) {
	for (OfferEntity offer : pOrder.getOffers()) {
	    if (offer.getContractor() != null) {
		if (offer.getContractor().getEmail().equals(loggedInUser)) {
		    return offer;
		}
	    }
	}
	return null;
    }

    public boolean checkIfOfferAlreadyAccepted(OrderEntity pOrder) {
	if (getSubmittedTenderFromOrder(pOrder) != null) {
	    if (getSubmittedTenderFromOrder(pOrder).isAccepted()) {
		return true;
	    }
	}
	return false;
    }

    public boolean checkIfLoggedInUserAlreadySubmitTender() {
	if (offer.getId() != null) {
	    return true;
	}
	return false;
    }

    public void fetchOfferIfExists() {
	OrderEntity order = orderDAO.fetchOrderByID(orderID);
	if (order != null) {
	    if (checkIfLoggedInUserAlreadySubmitTender(order)) {
		if (getSubmittedTenderFromOrder(order) != null) {
		    this.offer = getSubmittedTenderFromOrder(order);
		}
	    }
	}
    }

    public boolean checkIfLoggedInUserAlreadySubmitTender(OrderEntity pOrder) {
	if (getSubmittedTenderFromOrder(pOrder) != null) {
	    return true;
	}
	return false;
    }

    public List<OrderEntity> getAllOrders() {
	return orderDAO.fetchAllOrders();
    }

    public void setAllOrders(List<OrderEntity> allOrders) {
	this.allOrders = allOrders;
    }

    public OrderEntity getOrder() {
	return order;
    }

    public Long getOrderID() {
	return orderID;
    }

    public void setOrderID(Long orderID) {
	this.orderID = orderID;
    }

    public void setOrder(OrderEntity order) {
	this.order = order;
    }

    public OfferEntity getOffer() {
	return offer;
    }

    public void setOffer(OfferEntity offer) {
	this.offer = offer;
    }

    public String getLoggedInUser() {
	return loggedInUser;
    }

    public void setLoggedInUser(String loggedInUser) {
	this.loggedInUser = loggedInUser;
    }

}
