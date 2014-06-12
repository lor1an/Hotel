/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.controllers;

import com.epam.hotel.domain.Client;
import com.epam.hotel.domain.Order;
import com.epam.hotel.domain.Room;
import com.epam.hotel.domain.enums.OrderType;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lor1an
 */
@ManagedBean
@SessionScoped
public class OrderBuilder {

    private final long ONE_DAY = 86400000;

    private Room selectedRoom;
    private String orderType;
    private Client client;
    private int dayCount;
    private int payment;

    @ManagedProperty(value = "#{findRoomController}")
    private FindRoomController findRoomController;

    @ManagedProperty(value = "#{sessionController}")
    private SessionController sessionController;

    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public int getPayment() {
        return payment;
    }

    public void setPayment(int payment) {
        this.payment = payment;
    }

    public FindRoomController getFindRoomController() {
        return findRoomController;
    }

    public void setFindRoomController(FindRoomController frc) {
        this.findRoomController = frc;
    }

    public SessionController getSessionController() {
        return sessionController;
    }

    public void setSessionController(SessionController sessionController) {
        this.sessionController = sessionController;
    }

    public void show() {
        System.out.println(OrderType.valueOf(orderType));
        System.out.println(selectedRoom);
        System.out.println(findRoomController.getFrom());
        System.out.println(findRoomController.getTo());
    }

    public void makeOrder() {
        Order order = new Order();
    }

    public void init() {
        System.out.println("INIT");
        client = (Client) sessionController.getUser();
        System.out.println(client);
        long diff = findRoomController.getTo().getTime() - findRoomController.getFrom().getTime();
        System.out.println(diff);
        dayCount = (int) (diff / ONE_DAY);
        System.out.println(dayCount);
        payment = selectedRoom.getCost() * dayCount;
        System.out.println(payment);
        System.out.println(selectedRoom);
        System.out.println(findRoomController.getFrom());
        System.out.println(findRoomController.getTo());
    }

    public void goToReg() {
        System.out.println("reg");
        try {
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect("reg.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FindRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void goToMain() {
        System.out.println("main");
        try {
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FindRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
