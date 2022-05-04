package com.demo.st.country;

import com.demo.query.query;
import com.demo.st.Country;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class RequestAllCountries {

    private query query;
    private List<Country> data;



    @Override
    public String toString() {
        return   "\n "  + " " + data+" ";
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