package com.epam.hotel.beans;

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
import java.util.List;
import javax.ejb.Stateless;
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

    public List<Room> getFreeRooms(Date from, Date to,
            RoomComfort comfort, Integer bound) {
        RoomRepository rr = new RoomRepository(manager);
        OrderRepository or = new OrderRepository(manager);
        StringBuilder query = new StringBuilder();
        List<Room> firstList;
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
            firstList = q.getResultList();
            System.out.println(firstList);
        } else {
            firstList = rr.getAllRooms();
        }
        List<Room> secondList;
        secondList = or.getFreeRoomsWithFromOrders(from, to);
        System.out.println("------------");
        System.out.println(firstList);
        System.out.println(secondList);

        System.out.println("------------");
        firstList.removeAll(secondList);
        return firstList;

    }
}
