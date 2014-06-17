/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.DAO;

import com.epam.hotel.model.Order;
import com.epam.hotel.model.Room;
import com.epam.hotel.model.enums.OrderStatus;
import com.epam.hotel.model.enums.OrderType;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class OrderDAO {

    private EntityManager entityManager;

    public OrderDAO() {
    }

    public OrderDAO(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Order> getAllOrders() {
        TypedQuery<Order> orderQuery = entityManager.createNamedQuery("Order.getAllOrders", Order.class);
        return orderQuery.getResultList();
    }

    public Order getOrderById(int id) {
        TypedQuery<Order> orderQuery = entityManager.createNamedQuery("Order.getOrderById", Order.class);
        orderQuery.setParameter("id", id);
        return orderQuery.getSingleResult();
    }

    public void insertOrder(Order order) {
        entityManager.persist(order);
    }

    public void udpateOrder(Order order) {
        entityManager.merge(order);
    }

    public void deleteRoom(Order order) {
        Order temp = entityManager.find(Order.class, order.getId());
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

    public List<Room> getFreeRoomsWithFromOrders(Date arrival, Date departure) {
        TypedQuery<Room> roomQuery = entityManager.createNamedQuery("Order.getFreeRoomsWithOpenRequests", Room.class);
        roomQuery.setParameter("status", OrderStatus.CLOSED);
        roomQuery.setParameter("arrival", arrival);
        roomQuery.setParameter("departure", departure);
        try {
            List<Room> list = roomQuery.getResultList();
            return list;
        } catch (NoResultException e) {
            return null;
        }
    }
}
