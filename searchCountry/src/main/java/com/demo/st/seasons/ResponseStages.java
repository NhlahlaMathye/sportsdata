package com.demo.st.seasons;

import com.demo.query.Query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseStages {

    private Query query;
    private List<RequestStages> data;


    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<RequestStages> getData() {
        return data;
    }

    public void setData(List<RequestStages> data) {
        this.data = data;
    }
}
