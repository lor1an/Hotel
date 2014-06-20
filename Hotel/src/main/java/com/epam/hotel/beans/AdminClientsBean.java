/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.beans;

import com.epam.hotel.model.Client;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author lor1an
 */
@ManagedBean
@SessionScoped
public class AdminClientsBean {

    List<Client> clients;

    @EJB
    MessageBean messageEJB;

    public List<Client> getClients() {
        init();
        return clients;
    }

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public MessageBean getMessageEJB() {
        return messageEJB;
    }

    public void setMessageEJB(MessageBean messageEJB) {
        this.messageEJB = messageEJB;
    }

    public void init() {
        clients = messageEJB.getAllClients();
    }
    public void meth(){
        System.out.println("qweqweqwe");
    }
}
