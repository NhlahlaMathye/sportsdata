package com.demo.st.seasons;

import com.demo.query.Query;

import java.util.HashMap;
import java.util.List;

public class RequestSeasonResponse {

    private Query query;
    private List<RequestSeason> data;

    @Override
    public String toString()
    {
        return "" + data;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<RequestSeason> getData() {
        return data;
    }

    public void setData(List<RequestSeason> data) {
        this.data = data;
    }
}
