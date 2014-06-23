/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.beans.admin;

import com.epam.hotel.beans.MessageBean;
import com.epam.hotel.model.Order;
import com.epam.hotel.model.Room;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author lor1an
 */
@ManagedBean
@ViewScoped
public class AdminOrdersBean implements Serializable {

    @EJB
    MessageBean messageEJB;

    private LazyDataModel<Order> model;
    private Order selectedOrder;

    @PostConstruct
    public void init() {
        this.model = new LazyDataModel<Order>() {
            private static final long serialVersionUID = 1L;

            @Override
            public List<Order> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Order> result = messageEJB.getOrderResultList(first, pageSize, sortField, sortOrder, filters);
                model.setRowCount(messageEJB.countOrders(sortField, sortOrder, filters));
                return result;
            }
        };

    }

    public LazyDataModel<Order> getModel() {
        return model;
    }

    public void setModel(LazyDataModel<Order> model) {
        this.model = model;
    }

    public MessageBean getMessageEJB() {
        return messageEJB;
    }

    public void setMessageEJB(MessageBean messageEJB) {
        this.messageEJB = messageEJB;
    }

    public Order getSelectedOrder() {
        return selectedOrder;
    }

    public void setSelectedOrder(Order selectedOrder) {
        this.selectedOrder = selectedOrder;
    }

    public void tuc() {
        System.out.println("AAAAAAAAAA");
        System.out.println("selected orders");
    }

    public void showDialog() {
        if (selectedOrder != null) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.execute("multiCarDialog.show();");
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Invalid credentials"));
        }
    }
}
