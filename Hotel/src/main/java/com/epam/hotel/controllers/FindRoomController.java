/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.controllers;

import com.epam.hotel.domain.Room;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class FindRoomController {

    private Date from;
    private Date to;
    private String bounds;
    List<Room> freeRooms;
    @EJB
    MessageBean messageEJB;

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getBounds() {
        return bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }

    public List<Room> getFreeRooms() {
        return freeRooms;
    }

    public void setFreeRooms(List<Room> freeRooms) {
        this.freeRooms = freeRooms;
    }

    public void click() {
        System.out.println("click ---------------------------");
        System.out.println(from);
        System.out.println(to);

        freeRooms = messageEJB.getRoom(from, to);
        System.out.println(freeRooms);
        try {
            FacesContext.getCurrentInstance().getExternalContext().redirect("anotherpage.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FindRoomController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
