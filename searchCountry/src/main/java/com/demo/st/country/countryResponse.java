package com.demo.st.country;

import com.demo.query.query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class countryResponse {

    private query query;
    private List<fetchLeague> data;

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

    public List<fetchLeague> getData() {
        return data;
    }

    public void setData(List<fetchLeague> data) {
        this.data = data;
    }



}
