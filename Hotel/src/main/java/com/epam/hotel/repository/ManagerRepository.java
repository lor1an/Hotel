/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.repository;

import com.epam.hotel.domain.Manager;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class ManagerRepository {

    private EntityManager entityManager;

    public ManagerRepository() {
    }

    public ManagerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Manager> getAllManagers() {
        TypedQuery<Manager> managerQuery = entityManager.createNamedQuery("Manager.getAllManagers", Manager.class);
        return managerQuery.getResultList();
    }

    public Manager getManagerById(int id) {
        TypedQuery<Manager> managerQuery = entityManager.createNamedQuery("Manager.getManagerById", Manager.class);
        managerQuery.setParameter("id", id);
        return managerQuery.getSingleResult();
    }

    public Integer insertManager(Manager manager) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(manager);
            entityManager.getTransaction().commit();
            return manager.getId();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
                return null;
            }
        }
    }

    public void udpateManager(Manager manager) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(manager);
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }

    }

    public void deleteManager(Manager manager) {
        Manager temp = entityManager.find(Manager.class, manager.getId());
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
