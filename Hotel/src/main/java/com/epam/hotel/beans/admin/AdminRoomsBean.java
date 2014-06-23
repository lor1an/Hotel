/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.beans.admin;

import com.epam.hotel.beans.MessageBean;
import com.epam.hotel.model.Room;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author lor1an
 */
@ManagedBean
@ViewScoped
public class AdminRoomsBean implements Serializable {
    
    private static final long serialVersionUID = -1201944101993687165L;
    
    @EJB
    MessageBean messageEJB;
    
    private LazyDataModel<Room> model;
    
    @PostConstruct
    public void init() {
        this.model = new LazyDataModel<Room>() {
            private static final long serialVersionUID = 1L;
            
            @Override
            public List<Room> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
                List<Room> result = messageEJB.getResultList(first, pageSize, sortField, sortOrder, filters);
                model.setRowCount(messageEJB.count(sortField, sortOrder, filters));
                return result;
            }
        };
        
    }
    
    public LazyDataModel<Room> getModel() {
        return model;
    }
    
    public void setModel(LazyDataModel<Room> model) {
        this.model = model;
    }
    
}
