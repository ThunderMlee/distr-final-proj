package model;

/**
 *
 * @author RATHA
 */
public class Location {
    
    int ID;
    String name;
    int capacity;

    public Location(int Id, String name, int capacity) {
        this.ID = Id;
        this.name = name;
        this.capacity = capacity;
    }

    public Location() {
    }

    public Location(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
    
    //Getters & setters//////////////////////////
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
    ////////////////////////////////////////////
}
