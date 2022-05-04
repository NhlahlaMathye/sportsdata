package com.demo.st.players;

import com.demo.query.query;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponsePlayers {

    private query query;
    private List<RequestPlayers> data;

    @Override
    public String toString()
    {
        return " " + data;
    }

    public com.demo.query.query getQuery() {
        return query;
    }

    public void setQuery(com.demo.query.query query) {
        this.query = query;
    }

    public List<RequestPlayers> getData() {
        return data;
    }

    public void setData(List<RequestPlayers> data) {
        this.data = data;
    }
}
