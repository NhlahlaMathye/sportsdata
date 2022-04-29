package com.demo.st.country;

import com.demo.parsedata.data;
import com.demo.query.query;
import com.demo.st.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class countryResponse {

    private query query;
    private List<getLeagues> data;

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

    public List<getLeagues> getData() {
        return data;
    }

    public void setData(List<getLeagues> data) {
        this.data = data;
    }



}
