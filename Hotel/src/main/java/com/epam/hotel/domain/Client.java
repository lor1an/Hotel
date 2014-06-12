/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.NamedQueries;
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
@NamedQueries({
    @NamedQuery(name = "Client.getAllClients", query = "SELECT c from Client c"),
    @NamedQuery(name = "Client.getClientById", query = "SELECT c from Client c "
            + "WHERE c.id=:id"),
    @NamedQuery(name = "Client.getClientByLogin", query = "SELECT c from Client c"
            + " WHERE c.login=:login")

})
@Inheritance(strategy = InheritanceType.JOINED)
@PrimaryKeyJoinColumn
public class Client extends User {

    @Column(name = "name", length = 32)
    private String name;
    @Column(name = "surname", length = 32)
    private String surname;
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

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "client")
    private List<Order> clientOrders;

    public Client() {
    }

    public Client(String name, String surname, String phone, String email, String address, String city, String region, List<Order> clientOrders, String login, String password) {
        super(login, password);
        this.name = name;
        this.surname = surname;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.city = city;
        this.region = region;
        this.clientOrders = clientOrders;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

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

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    @Override
    public String toString() {
        return "Client{" + "name=" + name + ", surname=" + surname + ", phone=" + phone + ", email=" + email + ", address=" + address + ", city=" + city + ", region=" + region + ", clientOrders=" + clientOrders + '}';
    }

}
