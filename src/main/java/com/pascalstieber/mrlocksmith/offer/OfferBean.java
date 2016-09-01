package com.pascalstieber.mrlocksmith.offer;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pascalstieber.mrlocksmith.item.ItemDAO;
import com.pascalstieber.mrlocksmith.item.ItemEntity;
import com.pascalstieber.mrlocksmith.order.OrderDAO;
import com.pascalstieber.mrlocksmith.order.OrderEntity;

@Named
@ViewScoped
@ManagedBean
public class OfferBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private List<OrderEntity> allOrders;
    private Long orderID;

    private OrderEntity order;
    private OfferEntity offer;

    @Inject
    private OrderDAO orderDAO;
    @Inject
    private OfferDAO offerDAO;
    @Inject
    private ItemDAO itemDAO;

    @PostConstruct
    private void init() {
	OfferEntity newOffer = new OfferEntity();
	offer = newOffer;
    }

    public void addItemToOffer() {
	ItemEntity item = new ItemEntity();
	item.setOffer(offer);
	offer.setOrder(order);
	offer.addItem(item);
	// @TODO: Die items müssen sortiert werden, damit das leere bzw. neu
	// erstellte Item auf der GUI immer unten erscheint.
    }

    public String saveOffer() {
	offerDAO.saveNewOffer(offer);
	return "showAllOrders.xhtml?faces-redirect=true";
    }

    public List<OrderEntity> getAllOrders() {
	return orderDAO.fetchAllOrders();
    }

    public void setAllOrders(List<OrderEntity> allOrders) {
	this.allOrders = allOrders;
    }

    public OrderEntity getOrder() {
	if (this.orderID != null) {
	    order = orderDAO.fetchOrderByID(this.orderID);
	}
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

}
