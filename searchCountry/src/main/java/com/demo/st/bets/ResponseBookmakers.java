package com.demo.st.bets;

import com.demo.query.Query;

import java.util.List;

public class ResponseBookmakers {

    private Query query;
    private List<RequestBookmakers> data;

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

    public List<RequestBookmakers> getData() {
        return data;
    }

    public void setData(List<RequestBookmakers> data) {
        this.data = data;
    }
}
