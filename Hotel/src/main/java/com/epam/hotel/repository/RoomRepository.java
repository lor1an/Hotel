/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.repository;

import com.epam.hotel.domain.Room;
import com.epam.hotel.domain.User;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class RoomRepository {

    private EntityManager entityManager;

    public RoomRepository() {
    }

    public RoomRepository(EntityManager entityManager) {
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
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(room);
            entityManager.getTransaction().commit();

        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public void udpateRoom(Room room) {
            try {
            entityManager.getTransaction().begin();
            entityManager.merge(room);
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public void deleteRoom(Room room) {
        Room temp = entityManager.find(Room.class, room.getId());
        if (temp != null) {
            try {
                entityManager.getTransaction().begin();
                entityManager.remove(temp);
                entityManager.getTransaction().commit();
            } finally {
                if (entityManager.getTransaction().isActive()) {
                    entityManager.getTransaction().rollback();
                }
            }
        }
    }
}
