/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.domain;

import javax.faces.bean.ManagedBean;

/**
 *
 * @author Anatolii_Hlazkov
 */
@ManagedBean(name = "editor")
public class EditorBean {

    private String value = "This editor is provided by PrimeFaces";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
