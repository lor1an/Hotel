/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.beans;

import com.epam.hotel.model.Client;
import com.epam.hotel.model.Order;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author lor1an
 */
@ManagedBean
@SessionScoped
public class ProfileBean {

    List<Order> myOrders;
    Client client;

    @EJB
    MessageBean messageEJB;

    @ManagedProperty(value = "#{sessionController}")
    private SessionBean sessionController;

    public List<Order> getMyOrders() {
        init();
        return myOrders;
    }

    public void setMyOrders(List<Order> myOrders) {
        this.myOrders = myOrders;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public MessageBean getMessageEJB() {
        return messageEJB;
    }

    public void setMessageEJB(MessageBean messageEJB) {
        this.messageEJB = messageEJB;
    }

    public SessionBean getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionBean sessionController) {
        this.sessionController = sessionController;
    }

    public void init() {
        myOrders = ((Client) sessionController.getUser()).getClientOrders();
        client = (Client) sessionController.getUser();
        
    }

    public void editClientInformation() {
        System.out.println(client);
        messageEJB.editCurrentClient(client);
        sessionController.setUser(client);
    }
    public void qwe(){
        System.out.println("qqq");
    }
}
