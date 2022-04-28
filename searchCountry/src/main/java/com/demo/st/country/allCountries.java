package com.demo.st.country;

import com.demo.parsedata.data;
import com.demo.query.query;
import com.demo.st.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class allCountries {

    private query query;
    private List<Country> data;
    private int i;


    @Override
    public String toString() {
        return   " "  + " " + data+"\n";
    }


    public query getQuery() {
        return query;
    }

    public void setQuery(query query) {
        this.query = query;
    }

    public List<Country> getData() {
        return data;
    }

    public void setData(List<Country> data) {
        this.data = data;
    }



}
