package com.epam.hotel.controllers;

import com.epam.hotel.domain.Client;
import com.epam.hotel.domain.Room;
import com.epam.hotel.repository.ClientRepository;
import com.epam.hotel.repository.RoomRepository;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless

public class MessageBean implements Serializable {
    
    @PersistenceContext(unitName = "COLIBRI")
    private EntityManager manager;
    
    public Room getRoom() {
        RoomRepository rr = new RoomRepository(manager);
        return rr.getRoomById(1);
    }
    
    public List<Room> getRoom(Date from, Date to) {
        RoomRepository rr = new RoomRepository(manager);
        return rr.getFreeRooms(from, to);
    }
    
    public void createClientRepository(Client c) {
        ClientRepository cr = new ClientRepository(manager);
        cr.insertClient(c);
    }
    
}
