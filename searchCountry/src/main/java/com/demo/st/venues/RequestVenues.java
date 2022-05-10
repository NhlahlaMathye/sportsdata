package com.demo.st.venues;

public class RequestVenues {

    private int venue_id;
    private String name;
    private int capacity;
    private String city;
    private int country_id;

    @Override
    public String toString()
    {
        return "\n Venue Name: " + name + "\n Capacity: " + capacity + "\n City: " + city + "\n";
    }

    public int getVenue_id() {
        return venue_id;
    }

    public void setVenue_id(int venue_id) {
        this.venue_id = venue_id;
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}
