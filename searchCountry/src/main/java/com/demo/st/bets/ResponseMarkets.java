package com.demo.st.bets;

import com.demo.query.Query;

import java.util.List;

public class ResponseMarkets {

    private Query query;
    private List<RequestMarkets> data;

    @Override
    public String toString(){
        return " " + data;
    }

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<RequestMarkets> getData() {
        return data;
    }

    public void setData(List<RequestMarkets> data) {
        this.data = data;
    }
}
