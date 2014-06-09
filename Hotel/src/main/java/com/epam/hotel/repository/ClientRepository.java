/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.repository;

import com.epam.hotel.domain.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class ClientRepository {

    private EntityManager entityManager;

    public ClientRepository() {
    }

    public ClientRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Client> getAllClients() {
        TypedQuery<Client> clientQuery = entityManager.createNamedQuery("Client.getAllClients", Client.class);
        return clientQuery.getResultList();
    }

    public Client getClientById(int id) {
        TypedQuery<Client> clientQuery = entityManager.createNamedQuery("Client.getClientById", Client.class);
        clientQuery.setParameter("id", id);
        return clientQuery.getSingleResult();
    }

    public void insertClient(Client client) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(client);
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public void udpateClient(Client client) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(client);
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public void deleteClient(Client client) {
        Client temp = entityManager.find(Client.class, client.getId());
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
