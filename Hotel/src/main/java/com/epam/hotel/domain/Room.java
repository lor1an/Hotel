/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
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
    @NamedQuery(name = "Room.getRoomById", query = "SELECT r FROM Room r WHERE r.id=:id")})
public class Room implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(name = "room_number")
    private Integer roomNumber;

    @Column(name = "cost")
    private Integer cost;

    @Column(name = "capcity")
    private Integer capacity;

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

    @Override
    public String toString() {
        return "Room{" + "id=" + id + ", roomNumber=" + roomNumber + ", cost=" + cost + ", capacity=" + capacity + '}';
    }
}