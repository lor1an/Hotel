package com.epam.hotel.beans;

import com.epam.hotel.model.Client;
import com.epam.hotel.model.Order;
import com.epam.hotel.model.Room;
import com.epam.hotel.model.enums.OrderStatus;
import com.epam.hotel.model.enums.OrderType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author lor1an
 */
@ManagedBean(name = "orderBuilder")
@SessionScoped
public class OrderBean {
    
    private final long ONE_DAY = 86400000;
    
    private Room selectedRoom;
    private String orderType;
    private Client client;
    private int dayCount;
    private int payment;
    private String fromDate;
    private String toDate;
    private final String[] m = new String[]{"01 Jan", "02 Feb", "03 Mar", "04 Apr",
        "05 May", "06 Jun", "07 Jul", "08 Aug", "09 Sep", "10 Oct", "11 Nov", "12 Dec"};
    private final String[] y = new String[]{"2014", "2015", "2016", "2017",
        "2018", "2019", "2020", "2021", "2022", "2023", "2024"};
    private final List<String> months = new ArrayList(Arrays.asList(m));
    private final List<String> years = new ArrayList(Arrays.asList(y));
    
    @EJB
    MessageBean messageEJB;
    
    @ManagedProperty(value = "#{findRoomController}")
    private FindRoomBean findRoomController;
    
    @ManagedProperty(value = "#{sessionController}")
    private SessionBean sessionController;
    
    public Room getSelectedRoom() {
        return selectedRoom;
    }
    
    public void setSelectedRoom(Room selectedRoom) {
        this.selectedRoom = selectedRoom;
    }
    
    public String getOrderType() {
        return orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
        
    }
    
    public Client getClient() {
        return client;
    }
    
    public void setClient(Client client) {
        this.client = client;
    }
    
    public int getDayCount() {
        return dayCount;
    }
    
    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }
    
    public int getPayment() {
        return payment;
    }
    
    public void setPayment(int payment) {
        this.payment = payment;
    }
    
    public String getFromDate() {
        return fromDate;
    }
    
    public void setFromDate(String fromDate) {
        this.fromDate = fromDate;
    }
    
    public String getToDate() {
        return toDate;
    }
    
    public void setToDate(String toDate) {
        this.toDate = toDate;
    }
    
    public List<String> getMonths() {
        return months;
    }
    
    public List<String> getYears() {
        return years;
    }
    
    public FindRoomBean getFindRoomController() {
        return findRoomController;
    }
    
    public void setFindRoomController(FindRoomBean frc) {
        this.findRoomController = frc;
    }
    
    public SessionBean getSessionController() {
        return sessionController;
    }
    
    public void setSessionController(SessionBean sessionController) {
        this.sessionController = sessionController;
    }
    
    public MessageBean getMessageEJB() {
        return messageEJB;
    }
    
    public void setMessageEJB(MessageBean messageEJB) {
        this.messageEJB = messageEJB;
    }
    
    public void init() {
        client = (Client) sessionController.getUser();
        long diff = findRoomController.getTo().getTime() - findRoomController.getFrom().getTime();
        dayCount = (int) (diff / ONE_DAY);
        payment = selectedRoom.getCost() * dayCount;
        fromDate = findRoomController.getFrom().toString().substring(0, 10);
        toDate = findRoomController.getTo().toString().substring(0, 10);
        System.out.println(client);
    }
    
    public void makeOrder() {
        Order order = new Order();
        order.setManager(null);
        order.setClient(client);
        System.out.println(client);
        order.setFromDate(findRoomController.getFrom());
        order.setToDate(findRoomController.getTo());
        order.setPayment(payment);
        order.setRoom(selectedRoom);
        order.setStatus(OrderStatus.OPEN);
        order.setType(OrderType.valueOf(orderType));
        messageEJB.addOrder(order);
        try {
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FindRoomBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
