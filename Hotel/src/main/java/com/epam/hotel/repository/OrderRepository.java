/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.repository;

import com.epam.hotel.domain.Order;
import com.epam.hotel.domain.enums.OrderStatus;
import com.epam.hotel.domain.enums.OrderType;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class OrderRepository {

    private EntityManager entityManager;

    public OrderRepository() {
    }

    public OrderRepository(EntityManager entityManager) {
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
}
