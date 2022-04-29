package com.demo.st.team;

import com.demo.query.query;

import java.util.List;

public class fetchLeagueResponse {

    private query query;
    private List<fetchLeagues> data;


    @Override
    public String toString() {
        return  "\n query: " + query + " data: " + data ;
    }

    public query getQuery() {
        return query;
    }

    public void setQuery(query query) {
        this.query = query;
    }

    public List<fetchLeagues> getData() {
        return data;
    }

    public void setData(List<fetchLeagues> data) {
        this.data = data;
    }
}
