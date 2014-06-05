/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epam.hotel.domain;


import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author lor1an
 */
@Stateless
public class RoomsFacade extends AbstractFacade<Room> {
    @PersistenceContext(unitName = "COLIBRI")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RoomsFacade() {
        super(Room.class);
    }
    
}
