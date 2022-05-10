package com.demo.st.venues;

import com.demo.query.Query;

import java.util.List;

public class ResponseVenues {

    private Query query;
    private List<RequestVenues> data;

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

    public List<RequestVenues> getData() {
        return data;
    }

    public void setData(List<RequestVenues> data) {
        this.data = data;
    }
}
