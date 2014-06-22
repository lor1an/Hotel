/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.beans;

import com.epam.hotel.model.Room;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;

/**
 *
 * @author lor1an
 */
public class LazyRoomDataModel extends LazyDataModel<Room> {

    private List<Room> datasource;

    public LazyRoomDataModel(List<Room> datasource) {
        this.datasource = datasource;
    }

    @Override
    public Room getRowData(String rowKey) {
        for (Room room : datasource) {
            if (room.getId().equals(rowKey)) {
                return room;
            }
        }

        return null;
    }

    @Override
    public Object getRowKey(Room room) {
        return room.getId();
    }

    @Override
    public List<Room> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String, Object> filters) {
        List<Room> data = new ArrayList<Room>();

        //filter
        for (Room room : datasource) {
            boolean match = true;

            if (filters != null) {
                for (Iterator<String> it = filters.keySet().iterator(); it.hasNext();) {
                    try {
                        String filterProperty = it.next();
                        Object filterValue = filters.get(filterProperty);
                        String fieldValue = String.valueOf(room.getClass().getField(filterProperty).get(room));

                        if (filterValue == null || fieldValue.startsWith(filterValue.toString())) {
                            match = true;
                        } else {
                            match = false;
                            break;
                        }
                    } catch (Exception e) {
                        match = false;
                    }
                }
            }

            if (match) {
                data.add(room);
            }
        }

   
   

        //rowCount
        int dataSize = data.size();
        this.setRowCount(dataSize);

        //paginate
        if (dataSize > pageSize) {
            try {
                return data.subList(first, first + pageSize);
            } catch (IndexOutOfBoundsException e) {
                return data.subList(first, first + (dataSize % pageSize));
            }
        } else {
            return data;
        }
    }

}
