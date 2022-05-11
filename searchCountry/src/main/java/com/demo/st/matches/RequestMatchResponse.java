package com.demo.st.matches;

import com.demo.query.Query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.HashMap;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestMatchResponse {

    private Query query;
    private List<RequestMatch> data;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<RequestMatch> getData() {
        return data;
    }
    public void setData(List<RequestMatch> data) {
        this.data = data;
    }
}
