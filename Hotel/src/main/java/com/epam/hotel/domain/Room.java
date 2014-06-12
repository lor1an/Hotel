/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.domain;

import com.epam.hotel.domain.enums.RoomComfort;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 *
 * @author Anatolii_Hlazkov
 */
@Entity
@Table(name = "rooms")
@NamedQueries({
    @NamedQuery(name = "Room.getAllRooms", query = "SELECT r FROM Room r"),
    @NamedQuery(name = "Room.getRoomById", query = "SELECT r FROM Room r "
            + "WHERE r.id=:id"),
    @NamedQuery(name = "Room.getFreeRooms", query = "SELECT r  FROM Room r"
            + " WHERE NOT EXISTS"
            + "(SELECT o.room from Order o WHERE o.status!=:status)"),
    @NamedQuery(name = "Room.getFreeRooom", query = "SELECT r FROM Room r"
            + " WHERE EXISTS"
            + "(SELECT o.room from Order o WHERE o.status!=:status "
            + "AND (o.fromDate>:departure OR o.toDate<:arrival))")})
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "room_number")
    private Integer roomNumber;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "capacity")
    private Integer capacity;

    
    @Enumerated(EnumType.ORDINAL)
    private RoomComfort comfort;

    public Integer getId() {
        return id;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public Integer getCost() {
        return cost;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public RoomComfort getComfort() {
        return comfort;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setRoomNumber(Integer number) {
        this.roomNumber = number;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public void setComfort(RoomComfort comfort) {
        this.comfort = comfort;
    }

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", roomNumber=" + roomNumber + ", cost=" + cost + ", capacity=" + capacity + '}';
    }

}
