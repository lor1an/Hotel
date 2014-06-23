/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.beans.admin;

import com.epam.hotel.model.enums.RoomComfort;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.faces.convert.EnumConverter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author lor1an
 */
@FacesConverter(value = "comfortConverter")
public class ComfortConverter extends EnumConverter {

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value instanceof Enum) {

            return ((Enum<?>) value).name().toLowerCase();
        } else {
            throw new ConverterException(new FacesMessage("Value is not an enum: " + value.getClass()));
        }
    }

    @Override
    @SuppressWarnings({"rawtypes", "unchecked"})
    public Object getAsObject(FacesContext context, UIComponent component, String value) {

        try {
            return RoomComfort.valueOf(value);
        } catch (IllegalArgumentException e) {
            throw new ConverterException(new FacesMessage("Value is not an enum of type: "));
        }
    }
}
