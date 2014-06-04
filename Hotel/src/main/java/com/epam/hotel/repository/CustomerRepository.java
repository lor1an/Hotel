/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.repository;

import com.epam.hotel.domain.Customer;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class CustomerRepository {

    private EntityManager entityManager;

    public CustomerRepository() {
    }

    public CustomerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Customer> getAllCustomers() {
        TypedQuery<Customer> customerQuery = entityManager.createNamedQuery("Customer.getAllCustomers", Customer.class);
        return customerQuery.getResultList();
    }

    public Customer getCustomerById(int id) {
        TypedQuery<Customer> customerQuery = entityManager.createNamedQuery("Customer.getCustomerById", Customer.class);
        customerQuery.setParameter("id", id);
        return customerQuery.getSingleResult();
    }

    public void insertCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.persist(customer);
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public void udpateCustomer(Customer customer) {
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(customer);
            entityManager.getTransaction().commit();
        } finally {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
        }
    }

    public void deleteCustomer(Customer customer) {
        Customer temp = entityManager.find(Customer.class, customer.getId());
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
