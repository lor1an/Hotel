/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.controllers;

import com.epam.hotel.domain.Room;
import java.util.Comparator;
import org.primefaces.model.SortOrder;


public class LazySorter implements Comparator<Room> {

    private String sortField;

    private SortOrder sortOrder;

    public LazySorter(String sortField, SortOrder sortOrder) {
        this.sortField = sortField;
        this.sortOrder = sortOrder;
    }

    @Override
    public int compare(Room room1, Room room2) {
        try {
            Object value1 = Room.class.getField(this.sortField).get(room1);
            Object value2 = Room.class.getField(this.sortField).get(room2);

            int value = ((Comparable) value1).compareTo(value2);

            return SortOrder.ASCENDING.equals(sortOrder) ? value : -1 * value;
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
}
