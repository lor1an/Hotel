package com.epam.hotel.domain;

import com.epam.hotel.repository.RoomRepository;
import java.io.Serializable;
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

}
