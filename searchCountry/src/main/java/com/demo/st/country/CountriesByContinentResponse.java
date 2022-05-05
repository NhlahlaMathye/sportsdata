package com.demo.st.country;

import com.demo.query.ContinentQuery;
import com.demo.query.Query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CountriesByContinentResponse {

    private ContinentQuery query;
    HashMap<String, Country> data = new HashMap<>();

    @Override
    public String toString()
    {
        return query + " " + data;
    }

    public ContinentQuery getQuery() {
        return query;
    }

    public void setQuery(ContinentQuery query) {
        this.query = query;
    }

    public HashMap<String, Country> getData() {
        return data;
    }

    public void setData(HashMap<String, Country> data) {
        this.data = data;
    }
}
