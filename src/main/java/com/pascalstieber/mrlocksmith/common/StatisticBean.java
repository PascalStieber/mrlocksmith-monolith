package com.pascalstieber.mrlocksmith.common;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.pascalstieber.mrlocksmith.offer.OfferDAO;
import com.pascalstieber.mrlocksmith.offer.OfferEntity;
import com.pascalstieber.mrlocksmith.order.OrderDAO;

@Named
@ViewScoped
@ManagedBean
public class StatisticBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private OfferDAO offerDAO;
    @Inject
    private OrderDAO orderDAO;

    public String getLoggedInUsername() {
	if (FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal() != null) {
	    return FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal().toString();
	}
	return "k.A.";
    }

    public String getLastAcceptedContract() {
	Date now = new Date();
	Date lastContractAccepted = offerDAO.fetchLastContract();
	if (lastContractAccepted != null) {
	    long diff = now.getTime() - lastContractAccepted.getTime();
	    DateFormat formatter = new SimpleDateFormat("HH:mm:ss");
	    DateFormat hours = new SimpleDateFormat("HH");
	    DateFormat minutes = new SimpleDateFormat("mm");
	    String dateFormatted = formatter.format(diff);
	    String timeString = "%s Std.  %s Min.";
	    
	    timeString = String.format(timeString, hours.format(diff), hours.format(diff));
	    return timeString;
	}
	return "k.A.";
    }

    public String getOfferAmount() {
	return String.valueOf(offerDAO.fetchOfferAmount());
    }

    public String getOrderAmount() {
	return String.valueOf(orderDAO.fetchOrderAmount());
    }

    public String getAmountAcceptedOffers() {
	return String.valueOf(offerDAO.fetchAmountAcceptedOffers());
    }

}
