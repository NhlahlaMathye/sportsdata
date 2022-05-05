package com.demo.query;

public class ContinentQuery {

    private String apikey;
    private String continent;


    @Override
    public String toString()
    {
        return "API_KEY: " + apikey + " Continent: " + continent;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getContinent() {
        return continent;
    }

    public void setContinent(String continent) {
        this.continent = continent;
    }
}
