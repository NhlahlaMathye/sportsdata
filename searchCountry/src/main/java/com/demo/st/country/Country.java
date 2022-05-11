package com.demo.st.country;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Country {

    private int country_id;
    private String name;
    private String country_code;
    private String continent;

@Override
public String toString()
{
    return " " + name;
}
    // Getter Methods
    public int getCountry_id() {
        return country_id;
    }
    public String getName() {
        return name;
    }
    public String getCountry_code() {
        return country_code;
    }
    public String getContinent() {
        return continent;
    }
    // Setter Methods
    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }
    public void setContinent(String continent) {
        this.continent = continent;
    }
}
