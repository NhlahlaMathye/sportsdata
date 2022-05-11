package com.demo.st.topscorer;

import com.demo.query.Query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestScorerResponse {

    private Query query;
    private List<RequestScorer> data;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<RequestScorer> getData() {
        return data;
    }

    public void setData(List<RequestScorer> data) {
        this.data = data;
    }
}
