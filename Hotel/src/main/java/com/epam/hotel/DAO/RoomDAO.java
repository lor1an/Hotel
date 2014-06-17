/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.DAO;

import com.epam.hotel.model.Room;
import com.epam.hotel.model.enums.OrderStatus;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class RoomDAO {

    private EntityManager entityManager;

    public RoomDAO() {
    }

    public RoomDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Room> getAllRooms() {
        TypedQuery<Room> roomQuery = entityManager.createNamedQuery("Room.getAllRooms", Room.class);
        return roomQuery.getResultList();
    }

    public Room getRoomById(int id) {
        TypedQuery<Room> roomQuery = entityManager.createNamedQuery("Room.getRoomById", Room.class);
        roomQuery.setParameter("id", id);
        return roomQuery.getSingleResult();
    }

    public void insertRoom(Room room) {
        entityManager.persist(room);
    }

    public void udpateRoom(Room room) {
        entityManager.merge(room);
    }

    public void deleteRoom(Room room) {
        Room temp = entityManager.find(Room.class, room.getId());
        if (temp != null) {
            entityManager.remove(temp);
        }
    }

    public List<Room> getNoOpenRequestsRooms() {
        TypedQuery<Room> roomQuery = entityManager.createNamedQuery("Room.getRoomsNoOpenOrders", Room.class);
        roomQuery.setParameter("status", OrderStatus.CLOSED);
        try {
            List<Room> list = roomQuery.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        }
    }

    
}
