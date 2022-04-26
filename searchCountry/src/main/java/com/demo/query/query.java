package com.demo.query;

import com.demo.st.country.countryResponse;
import com.demo.st.team.fetchLeagueResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class query {

    private String apikey;
    private int country_id;
    private String country_name;
    private fetchLeagueResponse fetchLeagueResponse;
    private countryResponse countryResponse;


    @Override
    public String toString()
    {
        return "API_KEY: " + apikey + " Country_ID: " + country_name;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public countryResponse getCountryResponse() {
        return countryResponse;
    }

    public void setCountryResponse(countryResponse countryResponse) {
        this.countryResponse = countryResponse;
    }

    public fetchLeagueResponse getFetchLeagueResponse() {
        return fetchLeagueResponse;
    }

    public void setFetchLeagueResponse(fetchLeagueResponse fetchLeagueResponse) {
        this.fetchLeagueResponse = fetchLeagueResponse;
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
