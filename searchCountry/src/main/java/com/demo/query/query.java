package com.demo.query;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class query {

    private String apikey;
    private int country_id;


    @Override
    public String toString()
    {
        return "API_KEY: " + apikey + " country_id: " + country_id;
    }


    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public int getCountry_id() {
        return country_id;
    }

    public void setCountry_id(int country_id) {
        this.country_id = country_id;
    }
}
