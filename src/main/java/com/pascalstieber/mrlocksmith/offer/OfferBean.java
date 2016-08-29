package com.pascalstieber.mrlocksmith.offer;

import java.io.Serializable;
import java.util.HashSet;

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
    private HashSet<OrderEntity> allOrders;
    
    @Inject
    private OrderDAO orderDAO;
    
    public HashSet<OrderEntity> fetchAllOrders(){
	return null;
	
    }
    
    
    public HashSet<OrderEntity> getAllOrders() {
	return null;
//	return fetchAllOrders();
	
    }
    public void setAllOrders(HashSet<OrderEntity> allOrders) {
	this.allOrders = allOrders;
    }
    
}
