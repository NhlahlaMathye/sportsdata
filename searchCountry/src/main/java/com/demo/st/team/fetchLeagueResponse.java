package com.demo.st.team;

import com.demo.query.query;

import java.util.List;

public class fetchLeagueResponse {

    private query query;
    private RequestLeagueData data;


    @Override
    public String toString() {
        return  "\n Leagues : " + data ;
    }

    public query getQuery() {
        return query;
    }

    public void setQuery(query query) {
        this.query = query;
    }

    public RequestLeagueData getData() {
        return data;
    }

    public void setData(RequestLeagueData data) {
        this.data = data;
    }
}
