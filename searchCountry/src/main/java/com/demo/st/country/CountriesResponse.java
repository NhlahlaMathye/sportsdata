package com.demo.st.country;

import com.demo.query.query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class CountriesResponse {

    private query query;
    private List<RequestTeams> data;

    @Override
    public String toString() {
        return   " "  + " " + data;
    }


    public query getQuery() {
        return query;
    }

    public void setQuery(query query) {
        this.query = query;
    }

    public List<RequestTeams> getData() {
        return data;
    }

    public void setData(List<RequestTeams> data) {
        this.data = data;
    }



}
