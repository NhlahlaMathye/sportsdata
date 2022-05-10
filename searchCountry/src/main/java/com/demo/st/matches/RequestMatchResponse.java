package com.demo.st.matches;

import com.demo.query.Query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestMatchResponse {

    private Query query;
    HashMap<String, RequestMatch> data = new HashMap<>();

    @Override
    public String toString()
    {
        return "\n Match Data: " + data;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public HashMap<String, RequestMatch> getData() {
        return data;
    }

    public void setData(HashMap<String, RequestMatch> data) {
        this.data = data;
    }
}
