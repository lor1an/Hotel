/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.domain;

import com.epam.hotel.repository.RoomRepository;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Anatolii_Hlazkov
 */
@Stateless
public class RoomService {

    @PersistenceContext(unitName = "COLIBRI")
    private EntityManager manager;

    public List<Room> getRoom() {
        RoomRepository rr = new RoomRepository(manager);
        return rr.getAllRooms();
    }
}
