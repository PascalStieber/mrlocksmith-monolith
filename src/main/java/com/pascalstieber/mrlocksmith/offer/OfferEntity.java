package com.pascalstieber.mrlocksmith.offer;

import java.util.ArrayList;
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
    
    @OneToMany(cascade=CascadeType.ALL, mappedBy="offer", fetch = FetchType.LAZY)
    @OrderBy("id DESC")
    private List<ItemEntity> items = new ArrayList<>();

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
    
    
    
}
