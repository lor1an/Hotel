package com.epam.hotel.beans;

import com.epam.hotel.DAO.ClientDAO;
import com.epam.hotel.DAO.OrderDAO;
import com.epam.hotel.DAO.RoomDAO;
import com.epam.hotel.model.Client;
import com.epam.hotel.model.Order;
import com.epam.hotel.model.Room;
import com.epam.hotel.model.enums.OrderStatus;
import com.epam.hotel.model.enums.RoomComfort;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.primefaces.model.SortOrder;

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

    public Object getCountOfFreeRooms(Date from, Date to, RoomComfort comfort,
            Integer bound) {
        RoomDAO rr = new RoomDAO(manager);
        OrderDAO or = new OrderDAO(manager);
        StringBuilder query = new StringBuilder();
        List<Room> firstList;
        boolean flag;
        String noFreeRooms = " WHERE r NOT IN (SELECT o.room from Order o WHERE "
                + "(o.fromDate<=:departure AND o.toDate>=:arrival)"
                + " AND o.status!=:status)";
        String getCountOfAllRooms = "SELECT COUNT(r) FROM Room r";
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

        String test2Query = getCountOfAllRooms + noFreeRooms;

        Query q2 = manager.createQuery(test2Query);

        q2.setParameter("status", OrderStatus.CLOSED);
        q2.setParameter("arrival", from);
        q2.setParameter("departure", to);

        try {
            return q2.getSingleResult();
        } catch (NoResultException ex) {
            return null;
        }
    }

    public List<Room> getFreeRooms(Date from, Date to, RoomComfort comfort,
            Integer bound, int maxResults, int firstResult) {
        RoomDAO rr = new RoomDAO(manager);
        OrderDAO or = new OrderDAO(manager);
        StringBuilder query = new StringBuilder();
        List<Room> firstList;
        boolean flag;
        String noFreeRooms = " WHERE r NOT IN (SELECT o.room from Order o WHERE "
                + "(o.fromDate<=:departure AND o.toDate>=:arrival)"
                + " AND o.status!=:status)";
        String getAllRooms = "SELECT r FROM Room r";
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
        String testQuery = getAllRooms + noFreeRooms;

        Query q = manager.createQuery(testQuery);

        q.setParameter("status", OrderStatus.CLOSED);
        q.setParameter("arrival", from);
        q.setParameter("departure", to);

        q.setMaxResults(maxResults);
        q.setFirstResult(firstResult);
        firstList = q.getResultList();

        //        secondList = or.getFreeRoomsWithFromOrders(from, to);
        //        firstList.removeAll(secondList);
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

    public void editCurrentClient(Client client) {
        ClientDAO clientDAO = new ClientDAO(manager);
        clientDAO.udpateClient(client);
    }

    public List<Room> getResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Room> all = new ArrayList<Room>();
        all.addAll(getAll(first, pageSize, sortField, sortOrder, filters));
        return all;
    }

    public int count(String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return getAll(-1, -1, null, null, filters).size();
    }

    private Collection<? extends Room> getAll(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Room> q = cb.createQuery(Room.class);
        Root<Room> room = q.from(Room.class);
        q.select(room);

        Path<?> path = getPath(sortField, room);
        if (sortOrder == null) {
        } else if (sortOrder.equals(SortOrder.ASCENDING)) {
            q.orderBy(cb.asc(path));
        } else if (sortOrder.equals(SortOrder.DESCENDING)) {
            q.orderBy(cb.desc(path));
        } else if (sortOrder.equals(SortOrder.UNSORTED)) {
        }

        //filter
        Predicate filterCondition = cb.conjunction();
        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            if (!filter.getValue().equals("")) {
                Object insert;
                Path<String> pathFilter = getStringPath(filter.getKey(), room);
                if (filter.getKey().equals("comfort")) {
                    insert = getRoomEnum(filter.getValue().toString());
                } else {
                    insert = filter.getValue();
                }
                if (insert == null) {
                    return new ArrayList();
                }
                if (pathFilter != null) {
                    filterCondition = cb.and(filterCondition, cb.equal(pathFilter, insert));
                } else {
                    Path<?> pathFilterNonString = getPath(filter.getKey(), room);
                    filterCondition = cb.and(filterCondition, cb.equal(pathFilterNonString, filter.getValue()));
                }
            }
        }
        q.where(filterCondition);

        //pagination
        TypedQuery<Room> tq = manager.createQuery(q);
        if (pageSize >= 0) {
            tq.setMaxResults(pageSize);
        }
        if (first >= 0) {
            tq.setFirstResult(first);
        }
        return tq.getResultList();
    }

    private Path<?> getPath(String field, Root<Room> room) {
        Path<?> path = null;
        if (field == null || "id".equals(field)) {
            path = room.get("id");
        } else if ("cost".equals(field)) {
            path = room.get("cost");
        } else if ("comfort".equals(field)) {
            path = room.get("comfort");
        } else if ("capacity".equals(field)) {
            path = room.get("capacity");
        } else if ("roomNumber".equals(field)) {
            path = room.get("roomNumber");
        }
        return path;
    }

    private Path<String> getStringPath(String field, Root<Room> room) {
        Path<String> path = null;
        if (field == null || "id".equals(field)) {
            path = room.get("id");
        } else if ("cost".equals(field)) {
            path = room.get("cost");
        } else if ("comfort".equals(field)) {
            path = room.get("comfort");
        } else if ("capacity".equals(field)) {
            path = room.get("capacity");
        } else if ("roomNumber".equals(field)) {
            path = room.get("roomNumber");
        }
        return path;
    }

    private RoomComfort getRoomEnum(String value) {
        if (value.equalsIgnoreCase(RoomComfort.LUX.toString())) {
            return RoomComfort.LUX;
        } else if (value.equalsIgnoreCase(RoomComfort.SUIT.toString())) {
            return RoomComfort.SUIT;
        } else if (value.equalsIgnoreCase(RoomComfort.USUAL.toString())) {
            return RoomComfort.USUAL;
        } else {
            return null;
        }
    }

    public List<Order> getOrderResultList(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        CriteriaBuilder cb = manager.getCriteriaBuilder();
        CriteriaQuery<Order> q = cb.createQuery(Order.class);
        Root<Order> order = q.from(Order.class);
        q.select(order);

        //sort
        Path<?> path = getOrderPath(sortField, order);
        if (sortOrder == null) {
        } else if (sortOrder.equals(SortOrder.ASCENDING)) {
            q.orderBy(cb.asc(path));
        } else if (sortOrder.equals(SortOrder.DESCENDING)) {
            q.orderBy(cb.desc(path));
        } else if (sortOrder.equals(SortOrder.UNSORTED)) {
        }

        //filter
        Predicate filterCondition = cb.conjunction();
        for (Map.Entry<String, Object> filter : filters.entrySet()) {
            if (!filter.getValue().equals("")) {
                Path<String> pathFilter = getOrderStringPath(filter.getKey(), order);

                if (pathFilter != null) {
                    filterCondition = cb.and(filterCondition,
                            cb.like(pathFilter, "%" + filter.getValue() + "%"));
                } else {
                    Object insert;
                    Path<?> pathFilterNonString = getOrderPath(filter.getKey(), order);
                    if (filter.getKey().equals("status")) {
                        insert = getOrderStatusEnum(filter.getValue().toString());
                    } else if (filter.getKey().equals("fromDate") || filter.getKey().equals("toDate")) {
                        insert = getDate(filter.getValue().toString());
                    } else {
                        insert = filter.getValue();
                    }
                    if (insert == null) {
                        return new ArrayList();
                    }
                    filterCondition = cb.and(filterCondition, cb.equal(pathFilterNonString, insert));
                }
            }
        }
        q.where(filterCondition);

        //pagination
        TypedQuery<Order> tq = manager.createQuery(q);
        if (pageSize >= 0) {
            tq.setMaxResults(pageSize);
        }
        if (first >= 0) {
            tq.setFirstResult(first);
        }
        return tq.getResultList();
    }

    public int countOrders(String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        return getOrderResultList(-1, -1, null, null, filters).size();
    }

    private Path<?> getOrderPath(String field, Root<Order> order) {
        Path<?> path = null;
        if (field == null || "id".equals(field)) {
            path = order.get("id");
        } else if ("client.login".equals(field)) {
            path = order.get("client").get("login");
        } else if ("room.id".equals(field)) {
            path = order.get("room").get("id");
        } else if ("fromDate".equals(field)) {
            path = order.get("fromDate");
        } else if ("toDate".equals(field)) {
            path = order.get("toDate");
        } else if ("toDate".equals(field)) {
            path = order.get("toDate");
        } else if ("status".equals(field)) {
            path = order.get("status");
        } else if ("payment".equals(field)) {
            path = order.get("payment");
        }
        return path;
    }

    private Path<String> getOrderStringPath(String field, Root<Order> order) {
        Path<String> path = null;
        if ("client.login".equals(field)) {
            path = order.get("client").get("login");
        }
        return path;
    }

    private OrderStatus getOrderStatusEnum(String value) {
        if (value.equalsIgnoreCase(OrderStatus.CLOSED.toString())) {
            return OrderStatus.CLOSED;
        } else if (value.equalsIgnoreCase(OrderStatus.IN_PROGRESS.toString())) {
            return OrderStatus.IN_PROGRESS;
        } else if (value.equalsIgnoreCase(OrderStatus.OPEN.toString())) {
            return OrderStatus.OPEN;
        } else {
            return null;
        }
    }

    private Date getDate(String strToParse) {
        DateFormat df = new SimpleDateFormat("dd-MM-yyyy kk:mm:ss.S");
        try {
            
            return df.parse(strToParse + " 00:00:00.0");
        } catch (ParseException ex) {
            return null;
        }

    }
}
