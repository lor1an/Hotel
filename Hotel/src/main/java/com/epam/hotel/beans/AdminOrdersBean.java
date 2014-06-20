/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.beans;

import com.epam.hotel.model.Order;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lor1an
 */
@ManagedBean
@SessionScoped
public class AdminOrdersBean {

    List<Order> orders;

    @EJB
    MessageBean messageEJB;

    public List<Order> getOrders() {
        init();
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public MessageBean getMessageEJB() {
        return messageEJB;
    }

    public void setMessageEJB(MessageBean messageEJB) {
        this.messageEJB = messageEJB;
    }

    public void init() {
        orders = messageEJB.getAllOrders();
        
    }
}
