/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author RATHA
 */
public class Location {
    
    int Id;
    String name;
    int capacity;

    public Location(int Id, String name, int capacity) {
        this.Id = Id;
        this.name = name;
        this.capacity = capacity;
    }

    public Location() {
    }

    public Location(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
    
    
    
    
    

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    
    
}
