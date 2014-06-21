/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.beans;

import com.epam.hotel.model.Room;
import com.epam.hotel.model.enums.RoomComfort;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "findRoomController")
@SessionScoped
public class FindRoomBean {

    private Date from;
    private Date to;
    private String bounds;
    private String comfort;
    private Date today = new Date();
    List<Room> freeRooms;

    @EJB
    MessageBean messageEJB;

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public MessageBean getMessageEJB() {
        return messageEJB;
    }

    public void setMessageEJB(MessageBean messageEJB) {
        this.messageEJB = messageEJB;
    }

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

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public List<Room> getFreeRooms() {
        return freeRooms;
    }

    public void setFreeRooms(List<Room> freeRooms) {
        this.freeRooms = freeRooms;
    }

    public void click() {
        RoomComfort comfortToSend;
        Integer boundsToSend;
        if ("NOSELECT".equals(comfort) || comfort == null) {
            comfortToSend = null;
        } else {
            comfortToSend = RoomComfort.valueOf(comfort);
        }
        if ("NOSELECT".equals(bounds) || bounds == null) {
            boundsToSend = null;
        } else {
            boundsToSend = new Integer(bounds);
        }
        System.out.println(from);
        System.out.println(to);
        freeRooms = messageEJB.getFreeRooms(from, to, comfortToSend, boundsToSend);

        try {
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect("freerooms.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FindRoomBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void clear() {
        bounds = null;
        comfort = null;
        freeRooms = null;
        from = null;
        to = null;
    }

}
