package com.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class countryResponse {

    @JsonProperty("com.demo.query")
    private query query;
    @JsonProperty("com.demo.data")
    private data data;
    @JsonProperty("310")
    private getLeagues leagues;

    @Override
    public String toString() {
        return   " com.demo.query: " +  query + " com.demo.data:  " + data  +  " league_data:  " +  leagues;

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

    public void setQuery(query query) {
        this.query = query;
    }

    public data getObject() {
        return (data) data;
    }

    public void setObject(data data) {
        this.data = data;
    }


}
