package com.epam.hotel.domain;

import java.io.Serializable;
import javax.ejb.EJB;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class HelloBean implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    MessageBean messageEJB;

    public String getSayWelcome() {
       
        return messageEJB.getRoom().toString();
    }

}
