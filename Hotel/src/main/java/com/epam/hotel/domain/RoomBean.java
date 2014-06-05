/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.domain;

import java.util.List;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Anatolii_Hlazkov
 */
@ManagedBean
@SessionScoped
public class RoomBean {

    @EJB
    RoomService rs;
    List<Room> rooms;

    public List<Room> selectRooms() {
        rooms = rs.getRoom();
        return rooms;
    }
}
