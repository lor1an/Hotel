/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.beans;

import com.epam.hotel.model.Room;
import com.epam.hotel.model.enums.RoomComfort;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@ManagedBean(name = "findRoomController")
@SessionScoped
public class FindRoomBean {

    private Date from;
    private Date to;
    private String bounds;
    private String comfort;
    private Date today = new Date();
    List<Room> freeRooms;
    private int recordsPerPage = 2;
    private int countOfRecordsByRequest;
    private int currentPage = 1;
    private boolean nothingFound;
    private RoomComfort comfortToSend;
    private Integer boundsToSend;
    private int lastPage;

    @EJB
    MessageBean messageEJB;

    public Date getToday() {
        return today;
    }

    public void setToday(Date today) {
        this.today = today;
    }

    public MessageBean getMessageEJB() {
        return messageEJB;
    }

    public void setMessageEJB(MessageBean messageEJB) {
        this.messageEJB = messageEJB;
    }

    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }

    public String getBounds() {
        return bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }

    public String getComfort() {
        return comfort;
    }

    public void setComfort(String comfort) {
        this.comfort = comfort;
    }

    public List<Room> getFreeRooms() {
        return freeRooms;
    }

    public void setFreeRooms(List<Room> freeRooms) {
        this.freeRooms = freeRooms;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getRecordsPerPage() {
        return recordsPerPage;
    }

    public void setRecordsPerPage(int recordsPerPage) {
        this.recordsPerPage = recordsPerPage;
    }

    public int getCountOfRecordsByRequest() {
        return countOfRecordsByRequest;
    }

    public void setCountOfRecordsByRequest(int countOfRecordsByRequest) {
        this.countOfRecordsByRequest = countOfRecordsByRequest;
    }

    public boolean isNothingFound() {
        return nothingFound;
    }

    public void setNothingFound(boolean nothingFound) {
        this.nothingFound = nothingFound;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public void click() {

        if ("NOSELECT".equals(comfort) || comfort == null) {
            comfortToSend = null;
        } else {
            comfortToSend = RoomComfort.valueOf(comfort);
        }
        if ("NOSELECT".equals(bounds) || bounds == null) {
            boundsToSend = null;
        } else {
            boundsToSend = new Integer(bounds);
        }

        Object temp = messageEJB.getCountOfFreeRooms(from, to, comfortToSend, boundsToSend);
        if (temp == null) {
            nothingFound = true;
        } else {
            countOfRecordsByRequest = new Integer(temp.toString());
            findLastPage();
            firstPage();
            try {
                FacesContext.getCurrentInstance().getExternalContext().
                        redirect("freerooms.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(FindRoomBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    public void recalculate() {
        findLastPage();
        firstPage();
    }

    public void firstPage() {
        currentPage = 1;
        freeRooms = messageEJB.getFreeRooms(from, to, comfortToSend, boundsToSend,
                recordsPerPage, 0);
    }

    public void prevPage() {
        currentPage--;
        freeRooms = messageEJB.getFreeRooms(from, to, comfortToSend, boundsToSend,
                recordsPerPage, recordsPerPage * (currentPage - 1));
    }

    public void nextPage() {
        currentPage++;
        freeRooms = messageEJB.getFreeRooms(from, to, comfortToSend, boundsToSend,
                recordsPerPage, recordsPerPage * (currentPage - 1));

    }

    public void lastPage() {
        currentPage = lastPage;
        freeRooms = messageEJB.getFreeRooms(from, to, comfortToSend, boundsToSend,
                recordsPerPage, recordsPerPage * (currentPage - 1));
    }

    public void findLastPage() {
        if (countOfRecordsByRequest <= recordsPerPage) {
            lastPage = 1;
        }
        if (countOfRecordsByRequest % recordsPerPage == 0) {
            lastPage = countOfRecordsByRequest / recordsPerPage;
        }
        lastPage = (countOfRecordsByRequest / recordsPerPage) + 1;
    }

    public void clear() {
        bounds = null;
        comfort = null;
        freeRooms = null;
        from = null;
        to = null;
        currentPage = 0;
    }

}
