package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="property")

public class Property {

    public enum Statues {
        SOLD,
        OCCUPIED,
        FREE
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int property_id;
    private int price;
    private String type,picture,location;
    private Statues statues;

    public  Property(){

    }


    public Property(int price, String type,Statues statues,String picture,String location){
        this.price = price;
        this.type = type;
        this.statues = statues;
        this.picture = picture;
        this.location = location;
    }

    public Property(int property_id, int price, String type,Statues statues,String picture,String location){
        this.property_id = property_id;
        this.price = price;
        this.type = type;
        this.statues = statues;
        this.picture = picture;
        this.location = location;
    }


    public int getPropertyById() {
        return property_id;
    }

    public void setPropertyId(int property_id) {
        this.property_id = property_id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Statues getStatues() {
        return statues;
    }

    public void setStatues(Statues statues) {
        this.statues = statues;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

}
