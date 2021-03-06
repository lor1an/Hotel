/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.beans;

import com.epam.hotel.model.User;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;

/**
 *
 * @author lor1an
 */
@ManagedBean(name = "sessionController")
@SessionScoped
public class SessionBean {

    private String login;
    private String password;
    private User user;
    boolean loggedIn;
    boolean t = false;

    @EJB
    MessageBean messageEJB;
    
    public boolean isLoggedIn() {
        return loggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        this.loggedIn = loggedIn;
    }

    public boolean isT() {
        return t;
    }

    public void setT(boolean t) {
        this.t = t;
    }

    public MessageBean getMessageEJB() {
        return messageEJB;
    }

    public void setMessageEJB(MessageBean messageEJB) {
        this.messageEJB = messageEJB;
    }

    public User getUser() {
        return user;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void verifyUser() {
        User verUser = messageEJB.findClientByLogin(login);

        if (password != null && login != null && verUser != null
                && verUser.getPassword().equals(password)
                && verUser.getLogin().equals(login)) {

            user = verUser;
            loggedIn = true;
            RequestContext.getCurrentInstance().addCallbackParam("loggedIn", loggedIn);
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials"));
        }
    }

    public void logout() {
        login = null;
        password = null;
        user = null;
        loggedIn = false;
        try {
            FacesContext.getCurrentInstance().getExternalContext().
                    redirect("index.xhtml");
        } catch (IOException ex) {
            Logger.getLogger(FindRoomBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void some(){
        System.out.println("dfjslkdfjlksdjflk");
    }

}
