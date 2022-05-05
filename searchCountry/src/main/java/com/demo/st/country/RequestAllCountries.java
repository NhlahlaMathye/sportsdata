package com.demo.st.country;

import com.demo.query.Query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class RequestAllCountries {

    private Query query;
    private List<Country> data;



    @Override
    public String toString() {
        return   "\n "  + " " + data+" ";
    }


    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<Country> getData() {
        return data;
    }

    public void setData(List<Country> data) {
        this.data = data;
    }



}
