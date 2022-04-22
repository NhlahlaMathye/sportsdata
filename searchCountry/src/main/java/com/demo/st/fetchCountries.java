package com.demo.st;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;


@JsonIgnoreProperties(ignoreUnknown = true)
public class fetchCountries {

    @JsonProperty("country_id")
    private float country_id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("country_code")
    private String country_code;
    @JsonProperty("continent")
    private String continent;



    @Override
    public String toString(){
        return "country_id : " + country_id + " name : " + name + " country_code : " + country_code + " continent : " + continent;
    }

    public float getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry_code() {
        return country_code;
    }

    public void setCountry_code(String country_code) {
        this.country_code = country_code;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
