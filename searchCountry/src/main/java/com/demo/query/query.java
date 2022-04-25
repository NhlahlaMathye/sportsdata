package com.demo.query;

import com.demo.st.fetchLeagueResponse;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class query {

    private String apikey;
    private int country_id;
    private fetchLeagueResponse fetchLeagueResponse;

    @Override
    public String toString()
    {
        return "API_KEY: " + apikey + " Country_id: " + country_id;
    }

    public com.demo.st.fetchLeagueResponse getFetchLeagueResponse() {
        return fetchLeagueResponse;
    }

    public void setFetchLeagueResponse(com.demo.st.fetchLeagueResponse fetchLeagueResponse) {
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
