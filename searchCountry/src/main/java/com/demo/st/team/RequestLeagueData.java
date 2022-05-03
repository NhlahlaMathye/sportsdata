package com.demo.st.team;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestLeagueData {

    private RequestLeague data;

    @Override
    public String toString()
    {
        return "\n League data : " + data;
    }

    public RequestLeague getData() {
        return data;
    }

    public void setData(RequestLeague data) {
        this.data = data;
    }
}
