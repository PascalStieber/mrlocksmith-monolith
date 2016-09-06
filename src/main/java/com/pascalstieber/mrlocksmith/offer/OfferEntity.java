package com.pascalstieber.mrlocksmith.offer;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;

import com.pascalstieber.mrlocksmith.common.AbstractMRLSEntity;
import com.pascalstieber.mrlocksmith.contractor.ContractorEntity;
import com.pascalstieber.mrlocksmith.item.ItemEntity;
import com.pascalstieber.mrlocksmith.order.OrderEntity;

@Entity
public class OfferEntity extends AbstractMRLSEntity{

    private static final long serialVersionUID = 1L;
    
    @ManyToOne
    private OrderEntity order;
    
    @ManyToOne
    private ContractorEntity contractor;
    
    public ContractorEntity getContractor() {
        return contractor;
    }

    public void setContractor(ContractorEntity contractor) {
        this.contractor = contractor;
    }

    @OneToMany(cascade=CascadeType.ALL, mappedBy="offer", fetch = FetchType.LAZY, orphanRemoval = true)
    @OrderBy("id DESC")
    private List<ItemEntity> items = new ArrayList<>();

    private boolean accepted = false;
    private Date acceptedAt;
    
    public OrderEntity getOrder() {
        return order;
    }
    
    public void setOrder(OrderEntity order) {
        this.order = order;
    }
    
    public List<ItemEntity> getItems() {
        return items;
    }
    
    public void addItem(ItemEntity items) {
        this.items.add(items);
    }
    
    public void removeItem(ItemEntity pItem){
	this.items.remove(pItem);
    }

    public boolean isAccepted() {
	return accepted;
    }

    public void setAccepted(boolean accepted) {
	this.accepted = accepted;
    }

    public Date getAcceptedAt() {
	return acceptedAt;
    }

    public void setAcceptedAt(Date acceptedAt) {
	this.acceptedAt = acceptedAt;
    }
    
}
