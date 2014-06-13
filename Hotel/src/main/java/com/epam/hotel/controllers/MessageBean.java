package com.epam.hotel.controllers;

import com.epam.hotel.domain.Client;
import com.epam.hotel.domain.Order;
import com.epam.hotel.domain.Room;
import com.epam.hotel.domain.enums.RoomComfort;
import com.epam.hotel.repository.ClientRepository;
import com.epam.hotel.repository.OrderRepository;
import com.epam.hotel.repository.RoomRepository;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.ejb.Stateless;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
public class MessageBean implements Serializable {

    @PersistenceContext(unitName = "COLIBRI")
    private EntityManager manager;

    public Room getRoom() {
        RoomRepository rr = new RoomRepository(manager);
        return rr.getRoomById(1);
    }

    public List<Room> getRoom(Date from, Date to) {
        RoomRepository rr = new RoomRepository(manager);
        return rr.getFreeRooms(from, to);
    }

    public void createNewClient(Client c) {
        ClientRepository cr = new ClientRepository(manager);
        cr.insertClient(c);
    }

    public Client findClientByLogin(String login) {
        ClientRepository cr = new ClientRepository(manager);
        return cr.getClientByLogin(login);
    }

    public void addOrder(Order o) {
        OrderRepository or = new OrderRepository(manager);
        or.udpateOrder(o);
    }

    public void getFreeRooms(Date from, Date to,
            RoomComfort comfort, Integer bound) {
        StringBuilder query = new StringBuilder();
        boolean flag;
        query.append("SELECT DISTINCT r ");
        query.append("FROM Room r ");
        query.append("WHERE ");

        List<String> criteria = new ArrayList<String>();
        if (comfort != null) {
            criteria.add("r.comfort = :comfort");
        }
        if (bound != null) {
            criteria.add("r.capacity >= :more");
            criteria.add("r.capacity <= :less");
        }
        flag = criteria.isEmpty();
        if (!flag) {
            for (int i = 0; i < criteria.size(); i++) {
                if (i > 0) {
                    query.append(" AND ");
                }
                query.append(criteria.get(i));
            }
            Query q = manager.createQuery(query.toString());
            if (comfort != null) {
                q.setParameter("comfort", comfort);
            }
            if (bound != null) {
                q.setParameter("more", bound - 1);
                q.setParameter("less", bound);
            }
            List<Room> firstList = q.getResultList();
        }

    }
}
