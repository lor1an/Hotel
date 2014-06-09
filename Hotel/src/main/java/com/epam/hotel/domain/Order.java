/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.domain;

import com.epam.hotel.domain.enums.OrderStatus;
import com.epam.hotel.domain.enums.OrderType;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Anatolii_Hlazkov
 */
@Entity
@Table(name = "orders")
@NamedQueries({
    @NamedQuery(name = "Order.getAllOrders", query = "SELECT o from Order o"),
    
}
)
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @OneToOne
    private Room room;
    @Column(name = "from_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fromDate;
    @Column(name = "to_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date toDate;
    @ManyToOne(cascade = CascadeType.ALL)
    private Client client;
    @ManyToOne(cascade = CascadeType.ALL)
    private Manager manager;
    @Enumerated(EnumType.ORDINAL)
    private OrderType type;
    @Enumerated(EnumType.ORDINAL)
    private OrderStatus status;

    public Room getRoom() {
        return room;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public Integer getId() {
        return id;
    }

    public Client getClient() {
        return client;
    }

    public Manager getManager() {
        return manager;
    }

    public OrderType getType() {
        return type;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    public void setType(OrderType type) {
        this.type = type;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
