package com.demo.st.seasons;

import com.demo.query.Query;
import com.demo.st.team.RequestLeague;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestSeasonLeague {

    private Query query;
    private List<RequestLeague> data;

    @Override
    public String toString()
    {
        return " " + data;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<RequestLeague> getData() {
        return data;
    }

    public void setData(List<RequestLeague> data) {
        this.data = data;
    }
}
