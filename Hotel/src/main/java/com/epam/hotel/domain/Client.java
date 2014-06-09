/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

/**
 *
 * @author Anatolii_Hlazkov
 */
@Entity
@Table(name = "clients")
@NamedQuery(name = "Client.getAllClients", query = "SELECT c from Client c")
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn
public class Client extends User {

    @Column(name = "phone", length = 32)
    private String phone;
    @Column(name = "email", length = 32)
    private String email;
    @Column(name = "address", length = 32)
    private String address;
    @Column(name = "city", length = 32)
    private String city;
    @Column(name = "region", length = 32)
    private String region;

    @OneToMany(mappedBy = "client")
    private List<Order> clientOrders;

    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getCity() {
        return city;
    }

    public String getRegion() {
        return region;
    }

    public List<Order> getClientOrders() {
        return clientOrders;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setClientOrders(List<Order> clientOrders) {
        this.clientOrders = clientOrders;
    }
}
