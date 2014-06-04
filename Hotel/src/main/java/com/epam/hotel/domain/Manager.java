/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.domain;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "managers")
@NamedQuery(name = "Manager.getAllManagers", query = "SELECT m from Manager m")
@Inheritance(strategy = InheritanceType.JOINED)
//@PrimaryKeyJoinColumn
public class Manager extends User {

    @Column(name = "managerphone", length = 32)
    private String managerphone;

//    @OneToMany(mappedBy = "manager")
//    private List<Order> customerOrders;
//
//    public List<Order> getCustomerOrders() {
//        return customerOrders;
//    }
//
//    public void setCustomerOrders(List<Order> customerOrders) {
//        this.customerOrders = customerOrders;
//    }
    public String getManagerphone() {
        return managerphone;
    }

    public void setManagerphone(String managerphone) {
        this.managerphone = managerphone;
    }

}
