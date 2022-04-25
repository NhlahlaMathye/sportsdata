package com.demo.st;

import com.demo.query.query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class countryResponse {


    private query query;

    private com.demo.st.data data;

    private getLeagues leagues;

    @Override
    public String toString() {
        return   " com.demo.query.query: " +  query + " league_data:  " +  leagues;

    }

    public getLeagues getLeague() {
        return leagues;
    }

    public void setLeague(getLeagues leagues) {
        this.leagues = leagues;
    }

    public query getQuery() {
        return query;
    }

    public void setQuery(com.demo.query.query query) {
        this.query = query;
    }

    public data getObject() {
        return (data) data;
    }

    public void setObject(data data) {
        this.data = data;
    }


}
