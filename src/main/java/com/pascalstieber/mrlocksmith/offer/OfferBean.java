package com.pascalstieber.mrlocksmith.offer;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

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
    
    @Inject
    private OrderDAO orderDAO;
    
    
    @PostConstruct
    private void init(){
	setOrder(new OrderEntity());
    }
    
    public void printSomething(){
	System.out.println("println Hallo");
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

    public void setOrder(OrderEntity order) {
	this.order = order;
    }

    public Long getOrderID() {
	return orderID;
    }

    public void setOrderID(Long orderID) {
	this.orderID = orderID;
    }
    
}
