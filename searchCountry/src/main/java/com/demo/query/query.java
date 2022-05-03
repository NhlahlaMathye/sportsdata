package com.demo.query;

import com.demo.st.country.CountriesResponse;
import com.demo.st.team.fetchLeagueResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class query {

    private String apikey;
    private int country_id;
    private String country_name;
    private fetchLeagueResponse fetchLeagueResponse;
    private CountriesResponse countryResponse;


    @Override
    public String toString()
    {
        return "API_KEY: " + apikey + " country_id: " + countryResponse;
    }

    public String getCountry_name() {
        return country_name;
    }

    public void setCountry_name(String country_name) {
        this.country_name = country_name;
    }

    public CountriesResponse getCountryResponse() {
        return countryResponse;
    }

    public void setCountryResponse(CountriesResponse countryResponse) {
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
