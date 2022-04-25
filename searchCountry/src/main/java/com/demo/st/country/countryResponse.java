package com.demo.st.country;

import com.demo.parsedata.data;
import com.demo.query.query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)

public class countryResponse {


    private query query;
    private getLeagues leagues;

    @Override
    public String toString() {
        return   " query: " +  query + " league_data:  " +  leagues;
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

}
