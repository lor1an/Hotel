/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.controllers;

/**
 *
 * @author Anatolii_Hlazkov
 */
import com.epam.hotel.domain.Room;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.LazyDataModel;

@ManagedBean(name = "dtLazyView")
@ViewScoped
public class LazyView implements Serializable {

    private LazyDataModel<Room> lazyModel;

    private Room selectedRoom;

    @EJB
    private RoomService service;

    @PostConstruct
    public void init() {
        lazyModel = new LazyRoomDataModel(service.getAllRooms());
    }

    public LazyDataModel<Room> getLazyModel() {
        return lazyModel;
    }

    public Room getSelectedRoom() {
        return selectedRoom;
    }

    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }

    public void setService(RoomService service) {
        this.service = service;
    }

    public void onRowSelect(SelectEvent event) {
        FacesMessage msg = new FacesMessage("Room Selected", ((Room) event.getObject()).getId().toString());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
}
