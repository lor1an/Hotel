/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epam.hotel.domain;

/**
 *
 * @author Anatolii_Hlazkov
 */
public class Car {

    String id;
    String brand;
    int year;
    String color;
    int price;
    boolean soldState;

    public Car(String id, String brand, int year, String color, int price, boolean soldState) {
        this.id = id;
        this.brand = brand;
        this.year = year;
        this.color = color;
        this.price = price;
        this.soldState = soldState;
    }

    public String getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }

    public int getYear() {
        return year;
    }

    public String getColor() {
        return color;
    }

    public int getPrice() {
        return price;
    }

    public boolean getSoldState() {
        return soldState;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSoldState(boolean soldState) {
        this.soldState = soldState;
    }

    @Override
    public String toString() {
        return "Car{" + "id=" + id + ", brand=" + brand + ", year=" + year + ", color=" + color + ", price=" + price + ", soldState=" + soldState + '}';
    }

}
