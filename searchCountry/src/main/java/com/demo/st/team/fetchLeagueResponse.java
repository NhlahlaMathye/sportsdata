package com.demo.st.team;
import com.demo.query.query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class fetchLeagueResponse {

    private query query;
    private RequestLeague data;

    @Override
    public String toString() {
        return  "\n Data: " + query + " \n " + data + " \n ";
    }

    public query getQuery() {
        return query;
    }

    public void setQuery(query query) {
        this.query = query;
    }

    public RequestLeague getData() {
        return data;
    }

    public void setData(RequestLeague data) {
        this.data = data;
    }
}
