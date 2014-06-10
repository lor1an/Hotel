/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.controllers;

import com.epam.hotel.domain.Client;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class ClientView {

    private String name;
    private String surname;
    private String login;
    private String password;
    private String phone;
    private String email;
    private String city;
    private String region;

    @EJB
    MessageBean messageEJB;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void save() {
        Client c = new Client(name, surname,
                login, password, phone, email, city, region);
        System.out.println(name);
        System.out.println(surname);
        System.out.println(login);
        System.out.println(password);
        System.out.println(phone);
        System.out.println(email);
        System.out.println(city);
        System.out.println(region);
        messageEJB.createClientRepository(c);
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Welcome " + name + " " + surname));
    }
}
