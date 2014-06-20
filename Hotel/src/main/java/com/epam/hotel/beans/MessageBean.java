package com.epam.hotel.beans;

import com.epam.hotel.model.Client;
import com.epam.hotel.model.Order;
import com.epam.hotel.model.Room;
import com.epam.hotel.model.enums.RoomComfort;
import com.epam.hotel.DAO.ClientDAO;
import com.epam.hotel.DAO.OrderDAO;
import com.epam.hotel.DAO.RoomDAO;
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

    public List<Room> getAllRooms() {
        RoomDAO rr = new RoomDAO(manager);
        return rr.getAllRooms();
    }

    public void createNewClient(Client c) {
        ClientDAO cr = new ClientDAO(manager);
        cr.insertClient(c);
    }

    public Client findClientByLogin(String login) {
        ClientDAO cr = new ClientDAO(manager);
        return cr.getClientByLogin(login);
    }

    public void addOrder(Order o) {
        OrderDAO or = new OrderDAO(manager);
        or.udpateOrder(o);
    }

    public List<Room> getFreeRooms(Date from, Date to,
            RoomComfort comfort, Integer bound) {
        RoomDAO rr = new RoomDAO(manager);
        OrderDAO or = new OrderDAO(manager);
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

    public List<Order> getAllOrders() {
        OrderDAO or = new OrderDAO(manager);
        return or.getAllOrders();

    }

    public List<Client> getAllClients() {
        ClientDAO clientDAO = new ClientDAO(manager);
        return clientDAO.getAllClients();
    }

    public List<Order> getOrdersByClient(Client client) {
        OrderDAO or = new OrderDAO(manager);
        return or.getAllClientOrders();
    }
}
