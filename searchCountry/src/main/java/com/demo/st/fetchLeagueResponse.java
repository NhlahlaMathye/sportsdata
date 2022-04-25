package com.demo.st;

import com.demo.query.query;

public class fetchLeagueResponse {

    private query query;
    private fetchLeagues data;


    @Override
    public String toString() {
        return  "query: " + query + " data: " + data ;
    }

    public query getQuery() {
        return query;
    }

    public void setQuery(query query) {
        this.query = query;
    }

    public fetchLeagues getData() {
        return data;
    }

    public void setData(fetchLeagues data) {
        this.data = data;
    }
}
