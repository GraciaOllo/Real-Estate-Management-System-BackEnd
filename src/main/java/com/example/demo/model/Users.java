package com.example.demo.model;

import jakarta.persistence.*;

@Entity
@Table(name="users")
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

     private int id;
     private String password;
     private int contact;
     private  String name,email;

    public Users(){

    }
     public Users(int id,int contact, String email, String name){
        this.id = id;
        this.contact =contact;
        this.name=name;
        this.email=email;
    }
    public Users(int contact, String email, String name){

        this.contact =contact;
        this.name=name;
        this.email=email;
    }

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public int getContact(){
        return contact;
    }
    public void setContact(int contact){this.contact = contact;}
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
