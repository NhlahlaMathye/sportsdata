package com.demo.st.team;

import com.demo.query.Query;
import com.demo.st.team.RequestTeams;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)

public class RequestTeamsResponse {

    private Query query;
    private List<RequestTeams> data;

    @Override
    public String toString() {
        return   ""  + "" + data;
    }


    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }

    public List<RequestTeams> getData() {
        return data;
    }

    public void setData(List<RequestTeams> data) {
        this.data = data;
    }



}
