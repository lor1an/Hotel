/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.controllers;

import com.epam.hotel.domain.Room;
import com.epam.hotel.domain.enums.OrderStatus;
import com.epam.hotel.domain.enums.OrderType;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author lor1an
 */
@ManagedBean
@SessionScoped
public class OrderBuilder {

    private Room selectedRoom;
    private String orderType;

    @ManagedProperty(value = "#{findRoomController}")
    private FindRoomController findRoomController;

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

    public FindRoomController getFindRoomController() {
        return findRoomController;
    }

    public void setFindRoomController(FindRoomController frc) {
        this.findRoomController = frc;
    }

    public void show() {
        System.out.println(OrderType.valueOf(orderType));
        System.out.println(selectedRoom);
        System.out.println(findRoomController.getFrom());
        System.out.println(findRoomController.getTo());
    }
}
