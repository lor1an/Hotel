/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.repository;

import com.epam.hotel.domain.Client;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
        try {
            Client c = clientQuery.getSingleResult();
            return c;
        } catch (NoResultException exception) {
            return null;
        }
    }

    public void insertClient(Client client) {
        entityManager.persist(client);
    }

    public void udpateClient(Client client) {
        entityManager.merge(client);
    }

    public void deleteClient(Client client) {
        Client temp = entityManager.find(Client.class, client.getId());
        if (temp != null) {
            entityManager.remove(temp);
        }
    }

    public Client getClientByLogin(String login) {
        TypedQuery<Client> clientQuery = entityManager.createNamedQuery("Client.getClientByLogin", Client.class);
        clientQuery.setParameter("login", login);
        try {
            Client c = clientQuery.getSingleResult();
            return c;
        } catch (NoResultException exception) {
            return null;
        }
    }
}
